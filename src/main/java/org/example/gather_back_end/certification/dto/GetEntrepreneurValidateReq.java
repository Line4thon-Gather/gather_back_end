package org.example.gather_back_end.certification.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

public record GetEntrepreneurValidateReq(List<Business> businesses) {

    @Builder
    public record Business(
            String b_no,
            String start_dt,
            String p_nm
    ) {}

    public static GetEntrepreneurValidateReq from(List<CertificationEntrepreneurValidateReq> certificationReqs) {
        List<Business> businesses = certificationReqs.stream()
                .map(req -> Business.builder()
                        .b_no(req.b_no())
                        .start_dt(req.start_dt())
                        .p_nm(req.p_nm())
                        .build())
                .collect(Collectors.toList());

        return new GetEntrepreneurValidateReq(businesses);
    }

}
