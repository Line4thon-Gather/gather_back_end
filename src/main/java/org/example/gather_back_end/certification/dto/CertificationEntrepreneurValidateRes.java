package org.example.gather_back_end.certification.dto;

import lombok.Builder;

@Builder
public record CertificationEntrepreneurValidateRes(
        boolean isSuccess,
        String message
) {

    public static CertificationEntrepreneurValidateRes from(boolean isSuccess, String message) {
        return CertificationEntrepreneurValidateRes.builder()
                .isSuccess(isSuccess)
                .message(message)
                .build();
    }
}
