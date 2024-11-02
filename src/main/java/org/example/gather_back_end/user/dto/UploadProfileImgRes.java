package org.example.gather_back_end.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UploadProfileImgRes(
        @Schema(description = "프로필 이미지 public url", example = "https://objectstorage.ap-chuncheon-1.oraclecloud.com/n/axtc7cirqxfb/b/Togather/o/profileImg%2F4ab4db03-f9ef-4526-98e5-1121e45c7eec_profile2.png")
        String profileImgUrl
) {

    public static UploadProfileImgRes from(String profileImgUrl) {
        return UploadProfileImgRes.builder()
                .profileImgUrl(profileImgUrl)
                .build();
    }
}
