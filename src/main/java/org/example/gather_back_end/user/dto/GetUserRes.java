package org.example.gather_back_end.user.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.User;

@Builder
public record GetUserRes(
        String profileImgUrl,
        String name
) {
    public static GetUserRes from(User user) {
        return GetUserRes.builder()
                .profileImgUrl(user.getProfileImgUrl())
                .name(user.getName())
                .build();
    }
}
