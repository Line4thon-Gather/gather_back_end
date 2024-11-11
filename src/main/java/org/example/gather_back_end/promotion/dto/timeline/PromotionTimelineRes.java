package org.example.gather_back_end.promotion.dto.timeline;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record PromotionTimelineRes(
        @Schema(description = "총 기간", example = "43")
        int period,
        @Schema(description = "홍보 수단", example = "PRINTS")
        String category,
        List<Task> tasks
) {
}
