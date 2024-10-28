package org.example.gather_back_end.openai.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat-gpt", url = "${spring.openai.api-url}")
public interface OpenAiClient {

    @PostMapping(produces = "application/json")
    CustomOpenAiClientResponse postCustomOpenAiClientResponse(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody CustomOpenAiClientRequest requestDto
    );
}
