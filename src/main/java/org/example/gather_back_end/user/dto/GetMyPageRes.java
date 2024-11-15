package org.example.gather_back_end.user.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.User;

import java.util.List;

@Builder
public record GetMyPageRes(
        GetMyPageProfileInfo profileInfo, // 프로필 정보
        List<GetMyPagePromotionRes> promotionInfo // 홍보 전략 요청 사항
) {
}
