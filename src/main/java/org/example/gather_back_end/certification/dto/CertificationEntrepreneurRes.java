package org.example.gather_back_end.certification.dto;

import lombok.Builder;

@Builder
public record CertificationEntrepreneurRes(
        boolean isSuccess
) {

    public static CertificationEntrepreneurRes from(boolean isSuccess) {
        return CertificationEntrepreneurRes.builder()
                .isSuccess(isSuccess)
                .build();
    }
}
