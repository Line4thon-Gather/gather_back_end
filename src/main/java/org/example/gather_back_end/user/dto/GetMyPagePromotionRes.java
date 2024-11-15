package org.example.gather_back_end.user.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.PromotionRequest;

import java.time.LocalDateTime;

@Builder
public record GetMyPagePromotionRes(
        LocalDateTime createDay,
        String title,
        Integer period,
        Integer targetNumberOfPeople,
        Integer budget
) {
    public static GetMyPagePromotionRes from(PromotionRequest promotionRequest) {
        return GetMyPagePromotionRes.builder()
                .createDay(promotionRequest.getCreateAt())
                .title(promotionRequest.getTitle())
                .period(promotionRequest.getPeriod())
                .targetNumberOfPeople(promotionRequest.getTargetNumberOfPeople())
                .budget(promotionRequest.getBudget())
                .build();
    }
}
