package org.example.gather_back_end.certification.dto;

import lombok.Builder;

@Builder
public record CertificationUnivAuthRes(
        boolean isSuccess
) {

    public static CertificationUnivAuthRes from(boolean isSuccess) {
        return CertificationUnivAuthRes.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
