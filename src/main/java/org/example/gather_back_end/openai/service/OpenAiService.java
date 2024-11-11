package org.example.gather_back_end.openai.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.openai.client.OpenAiClient;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientRequest;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.util.constant.TimelinePrompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final OpenAiClient openAiClient;

    @Value("${spring.openai.api-key}")
    private String openAiApiKey;

    public CustomOpenAiClientResponse getOpenAiResponse(PromotionReq req) {
        String authHeader = "Bearer " + openAiApiKey;

        CustomOpenAiClientRequest realRequestDto = CustomOpenAiClientRequest.builder()
                .model("gpt-4o-mini")
                .addMessage("system", TimelinePrompt.SYSTEM_PROMPT)
//                .addMessage("user", OpenAiPrompt.USER1) // ex1 - req
//                .addMessage("assistant", OpenAiPrompt.ASSISTANT1) // ex1 - res
                .addMessage("user", TimelinePrompt.USER2) // ex2 - req
                .addMessage("assistant", TimelinePrompt.ASSISTANT2) // ex2 - res
                .addMessage("user", TimelinePrompt.USER3) // ex3 - req
                .addMessage("assistant", TimelinePrompt.ASSISTANT3) // ex3 - res
                .addMessage("user", req.toString() + " " + TimelinePrompt.FINAL_REQUEST_PROMPT) // 최종 요청 문구
                .build();

        return openAiClient.postCustomOpenAiClientResponse(authHeader, realRequestDto);
    }
}
