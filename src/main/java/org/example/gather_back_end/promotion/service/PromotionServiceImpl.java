package org.example.gather_back_end.promotion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.example.gather_back_end.openai.service.OpenAiService;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final OpenAiService openAiService;
    private final UserRepository userRepository;

    @Override
    // TODO: 데이터 저장 로직 추가
//    public List<PromotionRes> createPromotionStrategy(PromotionReq req, String providerId) {
    public List<PromotionRes> createPromotionStrategy(PromotionReq req) {
//        User user = userRepository.getByUsername(providerId);
        String result = openAiService.getOpenAiResponse(req).getContent();
        return parseContentToPromotionRes(result);
    }

    // Open AI로부터 받은 응답을 파싱
    private List<PromotionRes> parseContentToPromotionRes(String content) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 배열 문자열을 PromotionRes 리스트로 변환
            return objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, PromotionRes.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Open Ai 응답 메시지 파싱 실패 : " + content, e);
        }
    }
}
