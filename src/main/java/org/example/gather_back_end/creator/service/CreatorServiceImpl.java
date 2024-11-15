package org.example.gather_back_end.creator.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.WorkType;
import org.example.gather_back_end.portfolio.dto.GetPortfolioRes;
import org.example.gather_back_end.repository.PortfolioRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.WorkRepository;
import org.example.gather_back_end.util.format.WorkTypeConverter;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.PageResponse;
import org.example.gather_back_end.work.dto.GetWorkRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatorServiceImpl implements CreatorService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final WorkRepository workRepository;

    // 크리에이터 등록
    @Override
    public void createCreator(
            Long userId,
            String nickname,
            String introductionTitle,
            String introductionContent,
            String contactKakaoId,
            String contactEmail
    ){

        User user = userRepository.getById(userId);
        user.createCreatorInfo(user, nickname, introductionTitle, introductionContent, contactKakaoId, contactEmail);
        userRepository.save(user);
    };

    // 크리에이터 상세 페이지 조회
    @Override
    public GetCreatorRes getCreator(String nickname){

        User user = userRepository.getByNickname(nickname);

        List<GetPortfolioRes> getPortfolioResList = portfolioRepository.getAllByUser(user);
        List<GetWorkRes> getWorkResList = workRepository.findAllByUser(user);

        // GetCreatorRes에 하나하나 다 담아야함
        GetCreatorRes res = new GetCreatorRes(
                nickname,
                user.getProfileImgUrl(),
                user.getIntroductionTitle(),
                user.getIntroductionContent(),
                getPortfolioResList,
                getWorkResList,
                user.getContactKakaoId(),
                user.getContactEmail()
                );

        return res;

    }

    // 크리에이터 등록 초기 화면
    @Override
    public GetCreatorRes getCreatorInfo(Authentication authentication){

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        User user = userRepository.getByUsername(customOAuth2User.getUsername());

        List<GetPortfolioRes> getPortfolioResList = portfolioRepository.getAllByUser(user);
        List<GetWorkRes> getWorkResList = workRepository.findAllByUser(user);

        // GetCreatorRes에 하나하나 다 담아야함
        GetCreatorRes res = new GetCreatorRes(
                user.getNickname(),
                user.getProfileImgUrl(),
                user.getIntroductionTitle(),
                user.getIntroductionContent(),
                getPortfolioResList,
                getWorkResList,
                user.getContactKakaoId(),
                user.getContactEmail()
        );

        return res;

    }

    @Override
    public PageResponse<CreatorInfo> filteringCreator(Pageable pageable, Integer price, String category, String align) {

        // category를 WorkType으로 변환
        WorkType workCategory = null;
        if (category != null) {
            try {
                workCategory = WorkType.valueOf(category);
            } catch (IllegalArgumentException e) {
                log.error("유효하지 않은 카테고리: {}", category);
            }
        }

        // 데이터베이스에서 페이징 처리된 결과 가져오기
        Page<User> creators = userRepository.customFiltering(price, workCategory, align, pageable);

        // CreatorInfo로 변환
        List<CreatorInfo> creatorInfoList = creators.getContent().stream()
                .map(user -> CreatorInfo.from(
                        user,
                        user.getWorkList().stream()
                                .map(work -> WorkTypeConverter.toKorean(work.getCategory()))
                                .distinct()
                                .collect(Collectors.toList()),
                        user.getPortfolioList(),
                        align,    // align 파라미터 전달
                        category  // category 파라미터 전달
                ))
                .collect(Collectors.toList());

        // PageResponse로 변환
        return PageResponse.of(new PageImpl<>(creatorInfoList, pageable, creators.getTotalElements()));
    }
}
