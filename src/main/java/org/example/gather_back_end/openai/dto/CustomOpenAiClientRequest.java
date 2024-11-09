package org.example.gather_back_end.openai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public record CustomOpenAiClientRequest(
        @Schema(description = "사용할 OpenAI 모델의 이름", example = "gpt-4o-mini-2024-07-18")
        String model,

        @Schema(description = "모델에 제공할 메시지 목록")
        List<OpenAiRequestMessage> messages
) {

    @Builder
    public static class CustomOpenAiClientRequestBuilder {
        @Builder.Default
        private String model = "gpt-4o-mini";

        @Builder.Default
        private List<OpenAiRequestMessage> messages = new ArrayList<>();

        public CustomOpenAiClientRequestBuilder addMessage(String role, String content) {
            this.messages.add(new OpenAiRequestMessage(role, content));
            return this;
        }
    }
}
