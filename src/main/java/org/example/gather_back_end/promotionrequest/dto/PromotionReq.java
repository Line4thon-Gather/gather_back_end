package org.example.gather_back_end.promotionrequest.dto;

import org.example.gather_back_end.domain.WorkType;

public record PromotionReq(
        String title,
        Integer period,
        Long budget,
        WorkType firstMeans,
        WorkType secondMeans,
        WorkType thirdMeans

) {
}
