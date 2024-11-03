package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CertificationEntrepreneurValidateRes(
        @Schema(description = "사업자 인증 성공 여부", example = "true")
        boolean isSuccess,
        @Schema(description = "사업자 인증 결과 메시지", example = "사업자 등록 인증 성공")
        String message
) {

    public static CertificationEntrepreneurValidateRes from(boolean isSuccess, String message) {
        return CertificationEntrepreneurValidateRes.builder()
                .isSuccess(isSuccess)
                .message(message)
                .build();
    }
}
