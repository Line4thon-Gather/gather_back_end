package org.example.gather_back_end.certification.dto;

import lombok.Builder;

@Builder
public record CertificationUnivEmailRes(
        boolean isSuccess
) {

    public static CertificationUnivEmailRes of(boolean isSuccess) {
        return CertificationUnivEmailRes.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
