package org.example.gather_back_end.user.dto;

import lombok.Builder;

@Builder
public record UploadProfileImgRes(
        String profileImgUrl
) {

    public static UploadProfileImgRes from(String profileImgUrl) {
        return UploadProfileImgRes.builder()
                .profileImgUrl(profileImgUrl)
                .build();
    }
}
