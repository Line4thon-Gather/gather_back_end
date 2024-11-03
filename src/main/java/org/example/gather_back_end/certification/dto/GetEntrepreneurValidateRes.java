package org.example.gather_back_end.certification.dto;

import java.util.List;

public record GetEntrepreneurValidateRes(
        int request_cnt,
        int valid_cnt,
        String status_code,
        List<BusinessData> data
) {

    public record BusinessData(
            String b_no,
            String valid,
            RequestParam request_param,
            Status status
    ) {}

    public record RequestParam(
            String b_no,
            String start_dt,
            String p_nm
    ) {}

    public record Status(
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
