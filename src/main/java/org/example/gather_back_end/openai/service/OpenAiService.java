package org.example.gather_back_end.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.example.gather_back_end.openai.client.OpenAiClient;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientRequest;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final OpenAiClient openAiClient;

    @Value("${spring.openai.api-key}")
    private String openAiApiKey;

    public CustomOpenAiClientResponse getOpenAiResponse(CustomOpenAiClientRequest request) {
        String authHeader = "Bearer " + openAiApiKey;
        return openAiClient.postCustomOpenAiClientResponse(authHeader, request);
    }
}
