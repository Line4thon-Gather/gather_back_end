package org.example.gather_back_end.certification.dto;

import java.util.List;

public record GetEntrepreneurValidateReq(List<Business> businesses) {

    public record Business(
            String b_no,
            String start_dt,
            String p_nm
    ) {}
}
