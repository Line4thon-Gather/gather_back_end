package org.example.gather_back_end.openai.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientRequest;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OpenAiControllerApi {

    @Operation(summary = "Open AI 테스트용")
    @PostMapping
    SuccessResponse<CustomOpenAiClientResponse> requestToOpenAi(@RequestBody CustomOpenAiClientRequest request);
}
