package org.example.gather_back_end.promotion.dto.cost;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;

public record PromotionCostReq(
        @Schema(description = "사용자가 입력한 예산", example = "646000")
        Integer budget,

        @Schema(description = "1순위 홍보 수단 (필수)", example = "PRINTS")
        String firstMeans,

        @Schema(description = "2순위 홍보 수단 (빈 문자열 가능)", example = "VIDEO")
        String secondMeans,

        @Schema(description = "3순위 홍보 수단 (빈 문자열 가능)", example = "SNS_POST")
        String thirdMeans,

        @Schema(description = "인스타그램 홍보 기간", example = "20")
        Integer instagramPromotionPeriod
) {

}
