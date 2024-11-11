package org.example.gather_back_end.promotion.dto.timeline;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.example.gather_back_end.domain.WorkType;

public record PromotionTimelineReq(
        @Schema(description = "홍보 제목", example = "OOO 동아리 모집")
        String title,

        @Schema(description = "예상 모집 기간", example = "43")
        Integer period,

        @Schema(description = "목표 인원", example = "396")
        Integer targetNumberOfPeople,

        @Schema(description = "보유한 예산", example = "835000")
        Long budget,

        @NotNull
        @Schema(description = "1순위 홍보 수단 (필수)", example = "PRINTS")
        WorkType firstMeans,

        @Nullable
        @Schema(description = "2순위 홍보 수단 (null 가능)", example = "SNS_POST")
        WorkType secondMeans,

        @Nullable
        @Schema(description = "3순위 홍보 수단 (null 가능)", example = "VIDEO")
        WorkType thirdMeans
) {}
