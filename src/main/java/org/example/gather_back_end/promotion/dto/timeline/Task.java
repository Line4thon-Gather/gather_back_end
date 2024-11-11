package org.example.gather_back_end.promotion.dto.timeline;

import io.swagger.v3.oas.annotations.media.Schema;

public record Task(
        @Schema(description = "내용", example = "43")
        String name,

        @Schema(description = "시작일", example = "0")
        int start,

        @Schema(description = "마감일", example = "1")
        int end,

        @Schema(description = "한 줄 팁", example = "상호 배려와 사전 확정된 기획으로 원활한 촬영을 준비하세요!")
        String tip
) {
}
