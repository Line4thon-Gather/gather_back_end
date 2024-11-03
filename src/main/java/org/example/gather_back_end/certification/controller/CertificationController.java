package org.example.gather_back_end.certification.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailRes;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurRes;
import org.example.gather_back_end.certification.service.CertificationService;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certification")
public class CertificationController implements CertificationControllerApi {

    private final CertificationService certificationService;

    // 이메일 인증번호 받기
    @PostMapping("/univ/email")
    public SuccessResponse<CertificateUnivEmailRes> certificateUnivEmail(@RequestBody CertificateUnivEmailReq req)
            throws IOException {
        CertificateUnivEmailRes res = certificationService.certificateUnivEmail(req);
        return SuccessResponse.of(res);
    }

    // 이메일 인증번호 인증
    @PostMapping("/univ/auth")
    public SuccessResponse<CertificateUnivAuthRes> certificateUnivAuth(@RequestBody CertificateUnivAuthReq req) throws IOException {
        CertificateUnivAuthRes res = certificationService.certificateUnivAuth(req);
        return SuccessResponse.of(res);
    }

    // 사업자 번호 인증
    @PostMapping("/entrepreneur")
    public SuccessResponse<CertificationEntrepreneurRes> certificationEntrepreneur(@RequestBody CertificationEntrepreneurReq req) {
        CertificationEntrepreneurRes res = certificationService.certificationEntrepreneur(req);
        return SuccessResponse.of(res);
    }
}
