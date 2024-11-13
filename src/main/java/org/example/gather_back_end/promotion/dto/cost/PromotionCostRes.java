package org.example.gather_back_end.promotion.dto.cost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorReq;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;

public record PromotionCostRes(
        @Schema(description = "홍보 수단", example = "인쇄물")
        String means,

        @Schema(description = "소모 비용", example = "452,200")
        String cost,

        @Schema(description = "비용 비율", example = "70")
        Integer rate
) {

        @JsonIgnore
        public String getCostWithoutComma() {
                return cost.replace(",", "");
        }
}
