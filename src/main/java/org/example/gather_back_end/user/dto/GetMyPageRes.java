package org.example.gather_back_end.user.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.User;

import java.util.List;

@Builder
public record GetMyPageRes(
        String profileImgUrl, // 프로필 이미지
        String role, // 크리에이터인지 아닌지
        String email,
        List<GetMyPagePromotionRes> getMyPagePromotionResList
) {
}
