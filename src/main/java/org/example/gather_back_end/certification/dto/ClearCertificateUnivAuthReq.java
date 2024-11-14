package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClearCertificateUnivAuthReq(
        @Schema(description = "학교 이메일", example = "kms2171344@hansung.ac.kr")
        String email
) {
}
