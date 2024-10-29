package org.example.gather_back_end.openai.controller;

import org.example.gather_back_end.openai.dto.CustomOpenAiClientRequest;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/openai")
public class OpenAiController {

    @PostMapping("/test")
    public SuccessResponse<CustomOpenAiClientResponse> requestToOpenAi(@RequestBody CustomOpenAiClientRequest request) {
        CustomOpenAiClientResponse response = openAiService.getOpenAiResponse(request);
        return SuccessResponse.of(response);
    }
}
