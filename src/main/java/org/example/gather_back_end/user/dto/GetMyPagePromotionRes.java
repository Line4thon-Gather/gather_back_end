package org.example.gather_back_end.user.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.util.format.DateUtils;

@Builder
public record GetMyPagePromotionRes(
        String createDay,
        String title,
        Integer period,
        Integer targetNumberOfPeople,
        Integer budget
) {
    public static GetMyPagePromotionRes from(PromotionRequest promotionRequest) {

        String formattedDate = DateUtils.formatToDate(promotionRequest.getCreateAt());

        return GetMyPagePromotionRes.builder()
                .createDay(formattedDate)
                .title(promotionRequest.getTitle())
                .period(promotionRequest.getPeriod())
                .targetNumberOfPeople(promotionRequest.getTargetNumberOfPeople())
                .budget(promotionRequest.getBudget())
                .build();
    }
}
