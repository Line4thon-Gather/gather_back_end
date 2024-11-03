package org.example.gather_back_end.certification.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record EntrepreneurClientRes(
        int request_cnt,
        int match_cnt,
        String status_code,
        List<Data> data
) {
    public record Data(
            String b_no,
            String b_stt,
            String b_stt_cd,
            String tax_type,
            String tax_type_cd,
            String end_dt,
            String utcc_yn,
            String tax_type_change_dt,
            String invoice_apply_dt,
            String rbf_tax_type,
            String rbf_tax_type_cd
    ) {}
}
