package org.example.gather_back_end.promotion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.openai.service.OpenAiService;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostReq;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineRes;
import org.example.gather_back_end.repository.PromotionRequestRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final OpenAiService openAiService;
    private final UserRepository userRepository;
    private final PromotionRequestRepository promotionRequestRepository;

    @Override
    public List<PromotionTimelineRes> createPromotionStrategy(PromotionTimelineReq req, String providerId) {
        User user = userRepository.getByUsername(providerId);
        PromotionRequest promotionRequest = req.toPromotionRequest(req, user);
        promotionRequestRepository.save(promotionRequest);
        String result = openAiService.getAboutTimelineOpenAiResponse(req).getContent();
        return parseContentToTimelineRes(result);
    }

    @Override
    public List<PromotionCostRes> createPromotionCost(PromotionCostReq req) {
        String result = openAiService.getAboutCostOpenAiResponse(req).getContent();
        return parseContentToCostRes(result);
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
}
