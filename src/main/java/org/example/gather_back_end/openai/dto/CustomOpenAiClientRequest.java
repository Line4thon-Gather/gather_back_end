package org.example.gather_back_end.openai.dto;

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
    private String model;
    private List<OpenAiRequestMessage> messages;
}
