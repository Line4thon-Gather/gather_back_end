package org.example.gather_back_end.certification.dto;

public record CertificationUnivAuthReq(
        int code,
        String email,
        String univName
) {
}
