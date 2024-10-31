package org.example.gather_back_end.openai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiRequestMessage {

    @Schema(description = "메시지의 역할 (ex. user, assistant)", example = "user")
    private String role;

    @Schema(description = "모델에 전달할 메시지 내용", example = "안녕하세요, 오늘 날씨는 어떤가요?")
    private String content;
}
