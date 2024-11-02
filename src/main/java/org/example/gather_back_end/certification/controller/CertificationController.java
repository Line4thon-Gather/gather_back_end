package org.example.gather_back_end.certification.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailRes;
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
    public SuccessResponse<CertificationUnivEmailRes> certificationUnivEmail(@RequestBody CertificationUnivEmailReq req)
            throws IOException {
        CertificationUnivEmailRes res = certificationService.certificationUnivEmail(req);
        return SuccessResponse.of(res);
    }

    // 이메일 인증번호 인증
    @PostMapping("/univ/auth")
    public SuccessResponse<CertificationUnivAuthRes> certificationUnivAuth(@RequestBody CertificationUnivAuthReq req) throws IOException {
        CertificationUnivAuthRes res = certificationService.certificationUnivAuth(req);
        return SuccessResponse.of(res);
    }
}