package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CertificationUnivAuthRes(
        @Schema(description = "성공 여부", example = "true")
        boolean isSuccess
) {

    public static CertificationUnivAuthRes from(boolean isSuccess) {
        return CertificationUnivAuthRes.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
