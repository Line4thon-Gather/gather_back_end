package org.example.gather_back_end.openai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

public record CustomOpenAiClientRequest(
        @Schema(description = "사용할 OpenAI 모델의 이름", example = "gpt-4o-mini-2024-07-18")
        String model,

        @Schema(description = "모델에 제공할 메시지 목록")
        List<OpenAiRequestMessage> messages
) {

    @Builder
    public static CustomOpenAiClientRequest create(String model, List<OpenAiRequestMessage> messages) {
        return new CustomOpenAiClientRequest(
                model != null ? model : "gpt-4o-mini",
                messages != null ? messages : new ArrayList<>()
        );
    }

    public static CustomOpenAiClientRequestBuilder builder() {
        return new CustomOpenAiClientRequestBuilder();
    }

    public static class CustomOpenAiClientRequestBuilder {
        private String model = "gpt-4o-mini";
        private List<OpenAiRequestMessage> messages = new ArrayList<>();

        public CustomOpenAiClientRequestBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CustomOpenAiClientRequestBuilder addMessage(String role, String content) {
            this.messages.add(new OpenAiRequestMessage(role, content));
            return this;
        }

        public CustomOpenAiClientRequest build() {
            return new CustomOpenAiClientRequest(model, List.copyOf(messages));
        }
    }
}
