package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CertificationUnivEmailRes(
        @Schema(description = "성공 여부", example = "true")
        boolean isSuccess
) {

    public static CertificationUnivEmailRes from(boolean isSuccess) {
        return CertificationUnivEmailRes.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
