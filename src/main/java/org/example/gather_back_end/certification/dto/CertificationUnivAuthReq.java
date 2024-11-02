package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CertificationUnivAuthReq(
        @Schema(description = "인증 코드", example = "1234")
        int code,
        @Schema(description = "학교 이메일", example = "kms2171344@hansung.ac.kr")
        String email,
        @Schema(description = "대학교 이름", example = "한성대학교")
        String univName
) {
}
