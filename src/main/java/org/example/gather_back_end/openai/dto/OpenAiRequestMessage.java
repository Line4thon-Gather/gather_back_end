package org.example.gather_back_end.openai.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OpenAiRequestMessage(
        @Schema(description = "메시지의 역할 (ex. user, assistant)", example = "user")
        String role,

        @Schema(description = "모델에 전달할 메시지 내용", example = "안녕하세요, 오늘 날씨는 어떤가요?")
        String content
) {}
