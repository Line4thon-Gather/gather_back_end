package org.example.gather_back_end.promotion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.Work;
import org.example.gather_back_end.openai.service.OpenAiService;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostReq;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorReq;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorRes;
import org.example.gather_back_end.promotion.dto.promotion.PromotionRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineRes;
import org.example.gather_back_end.repository.PromotionRequestRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.util.format.Comma;
import org.example.gather_back_end.util.format.WorkTypeConverter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final OpenAiService openAiService;
    private final UserRepository userRepository;
    private final PromotionRequestRepository promotionRequestRepository;

    @Override
    public PromotionRes createAllPromotionInfo(PromotionTimelineReq req, String providerId) {

        // 타임라인 정보
        List<PromotionTimelineRes> timelineList = createPromotionStrategy(req, providerId);

        // 타임라인 정보로부터 인스타그램 홍보 기간 추출
        int instagramPromotionPeriod = getInstagramPromotionPeriod(timelineList);

        // 비용 관리 정보 호출을 위한 dto 변경
        PromotionCostReq promotionCostReq = req.toPromotionCostReq(instagramPromotionPeriod);

        // 비용 관리 정보
        List<PromotionCostRes> costList = createPromotionCost(promotionCostReq);
        log.info("비용 정보 : " +  costList.getFirst().cost());

        // 추천 크리에이터 정보 호출을 위한 dto 변경
        int firstMeansPrice = Comma.getWithoutComma(costList, 0);
        int secondMeansPrice = Comma.getWithoutComma(costList, 1);
        int thirdMeansPrice = Comma.getWithoutComma(costList, 2);

        BestCreatorReq bestCreatorReq = toBestCreatorReq(req, firstMeansPrice, secondMeansPrice, thirdMeansPrice);

        List<BestCreatorRes> creatorList = findBestCreator(bestCreatorReq);

        log.info(bestCreatorReq.toString());

        // 응답
        return new PromotionRes(timelineList, costList, creatorList);

    }

    // 타임라인 생성
    @Override
    public List<PromotionTimelineRes> createPromotionStrategy(PromotionTimelineReq req, String providerId) {
        User user = userRepository.getByUsername(providerId);
        PromotionRequest promotionRequest = req.toPromotionRequest(req, user);
        promotionRequestRepository.save(promotionRequest);
        String result = openAiService.getAboutTimelineOpenAiResponse(req).getContent();
        return parseContentToTimelineRes(result);
    }

    // 비용 관리
    @Override
    public List<PromotionCostRes> createPromotionCost(PromotionCostReq req) {
        String result = openAiService.getAboutCostOpenAiResponse(req).getContent();
        return parseContentToCostRes(result);
    }

    // 크리에이터 추천
    @Override
    public List<BestCreatorRes> findBestCreator(BestCreatorReq req) {

        /**
         * 크리에이터로 등록한 User를 찾는다.
         */
        List<User> allCreators = userRepository.findAllCreators();
        List<BestCreatorRes> bestCreators = new ArrayList<>();
        Set<String> uniqueUsernames = new HashSet<>();

        if (!req.firstMeans().isEmpty() && !req.secondMeans().isEmpty() && !req.thirdMeans().isEmpty()) {
            log.info("세 개 수단 모두 등록됨");

            // 각 수단에 맞는 User 필터링
            List<User> firstMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.firstMeans()) && work.getStartPrice() <= req.firstMeansPrice()))
                    .limit(3)
                    .toList();

            List<User> secondMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.secondMeans()) && work.getStartPrice() <= req.secondMeansPrice()))
                    .limit(2)
                    .toList();

            List<User> thirdMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.thirdMeans()) && work.getStartPrice() <= req.thirdMeansPrice()))
                    .limit(1)
                    .toList();

            // 중복된 사용자 제거하면서 BestCreatorRes 리스트 생성
            firstMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);

            secondMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);

            thirdMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);
        }  else if (!req.firstMeans().isEmpty() && !req.secondMeans().isEmpty()) {
            log.info("두 개 수단 등록됨");

            // firstMeans에서 4개, secondMeans에서 2개 필터링
            List<User> firstMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.firstMeans()) && work.getStartPrice() <= req.firstMeansPrice()))
                    .limit(4)
                    .toList();

            List<User> secondMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.secondMeans()) && work.getStartPrice() <= req.secondMeansPrice()))
                    .limit(2)
                    .toList();

            // 중복된 사용자 제거하면서 BestCreatorRes 리스트 생성
            firstMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);

            secondMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);

        } else if (!req.firstMeans().isEmpty()) {
            log.info("한 개 수단만 등록됨");

            // firstMeans에서만 6개 필터링
            List<User> firstMeansUsers = allCreators.stream()
                    .filter(user -> user.getWorkList().stream()
                            .anyMatch(work -> work.getCategory().name().equals(req.firstMeans()) && work.getStartPrice() <= req.firstMeansPrice()))
                    .limit(6)
                    .toList();

            // 중복된 사용자 제거하면서 BestCreatorRes 리스트 생성
            firstMeansUsers.stream()
                    .map(this::convertToRes)
                    .filter(bestCreator -> uniqueUsernames.add(bestCreator.nickname())) // 중복 검사
                    .forEach(bestCreators::add);
        }

        log.info(bestCreators.toString());
        return bestCreators;
    }

    // Open AI로부터 받은 응답을 파싱
    private List<PromotionTimelineRes> parseContentToTimelineRes(String content) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 배열 문자열을 PromotionRes 리스트로 변환
            return objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, PromotionTimelineRes.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("[타임라인 생성] Open Ai 응답 메시지 파싱 실패 : " + content, e);
        }
    }

    // Open AI로부터 받은 응답을 파싱
    private List<PromotionCostRes> parseContentToCostRes(String content) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 배열 문자열을 PromotionRes 리스트로 변환
            return objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, PromotionCostRes.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("[비용 관리] Open Ai 응답 메시지 파싱 실패 : " + content, e);
        }
    }

    // 인스타그램 홍보 기간 추출
    private int getInstagramPromotionPeriod(List<PromotionTimelineRes> timelineRes) {
        return timelineRes.stream()
                .flatMap(timeline -> timeline.tasks().stream())
                .filter(task -> "게시_인스타그램".equals(task.name()))
                .mapToInt(task -> task.end() - task.start())
                .findFirst()
                .orElse(0);
    }

    private BestCreatorReq toBestCreatorReq(
            PromotionTimelineReq req,
            int firstMeansPrice,
            int secondMeansPrice,
            int thirdMeansPrice
    ) {
        return new BestCreatorReq(
                req.firstMeans().toString(),
                firstMeansPrice,
                req.secondMeans().toString(),
                secondMeansPrice,
                req.thirdMeans().toString(),
                thirdMeansPrice
        );
    }

    private BestCreatorRes convertToRes(User user) {
        // WorkType을 한글명으로 변환하여 리스트로 수집
        List<String> workTypesInKorean = user.getWorkList().stream()
                .map(Work::getCategory)
                .distinct() // 중복 제거
                .map(WorkTypeConverter::toKorean) // 유틸리티 클래스 사용
                .collect(Collectors.toList());

        // startPrice에 천 단위 콤마 붙이기
        int startPriceValue = user.getWorkList().isEmpty() ? 0 : user.getWorkList().get(0).getStartPrice();
        String startPrice = Comma.formatWithComma(startPriceValue);

        String thumbnailUrl = user.getPortfolioList().isEmpty() ? "" : user.getPortfolioList().get(0).getThumbnailImgUrl();

        return new BestCreatorRes(
                user.getNickname(),
                workTypesInKorean, // 한글로 변환된 WorkType 리스트
                user.getIntroductionTitle(),
                startPrice,
                thumbnailUrl
        );
    }

}
