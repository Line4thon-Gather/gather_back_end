package org.example.gather_back_end.openai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomOpenAiClientResponse {

    @Schema(description = "OpenAI 응답의 고유 식별자", example = "chatcmpl-AO53rQDGHQVoiipGuDYdQnySlXopD")
    private String id;

    @Schema(description = "반환된 객체의 유형", example = "chat.completion")
    private String object;

    @Schema(description = "응답이 생성된 타임스탬프", example = "1730302691")
    private long created;

    @Schema(description = "응답을 생성하는 데 사용된 모델", example = "gpt-4o-mini-2024-07-18")
    private String model;

    @Schema(description = "모델이 생성한 선택 목록")
    private List<Choice> choices;

    @Schema(description = "요청 및 응답에 사용된 토큰 정보")
    private Usage usage;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {

        @Schema(description = "응답 목록에서의 선택 인덱스", example = "0")
        private int index;

        @Schema(description = "응답의 메시지 내용 및 역할")
        private Message message;

        @Schema(description = "응답 완료 이유", example = "stop")
        private String finishReason;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        @Schema(description = "응답에서 발신자의 역할", example = "assistant")
        private String role;

        @Schema(description = "메시지 내용", example = "This is a test!")
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {

        @Schema(description = "프롬프트에서 사용된 토큰 수", example = "15")
        private int promptTokens;

        @Schema(description = "완성된 응답에서 사용된 토큰 수", example = "20")
        private int completionTokens;

        @Schema(description = "전체 사용된 토큰 수", example = "35")
        private int totalTokens;
    }
}
