package org.example.gather_back_end.openai.client;

import org.example.gather_back_end.openai.dto.CustomOpenAiClientRequest;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat-gpt", url = "${openai.api-url}")
public interface OpenAiClient {

    @PostMapping(produces = "application/json")
    CustomOpenAiClientResponse postCustomOpenAiClientResponse(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody CustomOpenAiClientRequest requestDto
    );
}
