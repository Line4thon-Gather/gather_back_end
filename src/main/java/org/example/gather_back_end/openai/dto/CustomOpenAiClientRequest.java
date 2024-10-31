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
public class CustomOpenAiClientRequest {

    @Schema(description = "사용할 OpenAI 모델의 이름", example = "gpt-4o-mini-2024-07-18")
    private String model;

    @Schema(description = "모델에 제공할 메시지 목록")
    private List<OpenAiRequestMessage> messages;
}
