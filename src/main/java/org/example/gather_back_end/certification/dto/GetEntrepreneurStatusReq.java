package org.example.gather_back_end.certification.dto;

import java.util.List;

public record GetEntrepreneurStatusReq(
        List<String> b_no
) {

    public static GetEntrepreneurStatusReq of(String... b_no) {
        return new GetEntrepreneurStatusReq(List.of(b_no));
    }
}
