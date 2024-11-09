package org.example.gather_back_end.promotion.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record Task(
        @Schema(description = "내용", example = "43")
        String name,

        @Schema(description = "시작일", example = "0")
        int start,

        @Schema(description = "마감일", example = "1")
        int end
) {
}
