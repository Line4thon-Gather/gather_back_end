package org.example.gather_back_end.certification.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CertificationEntrepreneurValidateReq(
        @Schema(description = "사업자 등록번호", example = "1496200716")
        String b_no,
        @Schema(description = "개업일자", example = "20240603")
        String start_dt,
        @Schema(description = "대표자 성명", example = "최용석")
        String p_nm
) {
}
