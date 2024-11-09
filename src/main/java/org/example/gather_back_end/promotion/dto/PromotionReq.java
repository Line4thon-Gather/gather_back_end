package org.example.gather_back_end.promotion.dto;

import org.example.gather_back_end.domain.WorkType;

public record PromotionReq(
        String title,
        Integer period,
        Integer targetNumberOfPeople,
        Long budget,
        WorkType firstMeans,
        WorkType secondMeans,
        WorkType thirdMeans

) {
}
