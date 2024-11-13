package org.example.gather_back_end.promotion.dto.creator;

import io.swagger.v3.oas.annotations.media.Schema;

public record BestCreatorReq(
        @Schema(description = "1순위 홍보 수단 (필수)", example = "PRINTS")
        String firstMeans,

        @Schema(description = "1순위 홍보 수단에 소요되는 비용", example = "70000")
        int firstMeansPrice,

        @Schema(description = "2순위 홍보 수단 (빈 문자열 가능)", example = "VIDEO")
        String secondMeans,

        @Schema(description = "2순위 홍보 수단에 소요되는 비용", example = "30000")
        int secondMeansPrice,

        @Schema(description = "3순위 홍보 수단 (빈 문자열 가능)", example = "SNS_POST")
        String thirdMeans,

        @Schema(description = "3순위 홍보 수단에 소요되는 비용", example = "0")
        int thirdMeansPrice
) {
}
