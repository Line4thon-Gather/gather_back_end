package org.example.gather_back_end.certification.dto;

import java.util.List;

public record GetEntrepreneurStatusReq(
        List<String> b_no
) {
}