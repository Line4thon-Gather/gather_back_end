package org.example.gather_back_end.certification.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailRes;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;
import org.example.gather_back_end.certification.service.CertificationService;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.jwt.util.JwtUtil;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certification")
public class CertificationController implements CertificationControllerApi {

    private final JwtUtil jwtUtil;
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
    public SuccessResponse<CertificateUnivAuthRes> certificateUnivAuth(
            Authentication authentication,
            @RequestBody CertificateUnivAuthReq req
    ) throws IOException {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = user.getUsername();
        log.info("@@@@@@ providerId : " + providerId);
        CertificateUnivAuthRes res = certificationService.certificateUnivAuth(req, providerId);
        return SuccessResponse.of(res);
    }

    // 사업자 번호 인증 (validate + status 검증)
    @PostMapping("/entrepreneur")
    public SuccessResponse<?> certificationEntrepreneur(
            Authentication authentication,
            @RequestBody CertificationEntrepreneurValidateReq req
    ) {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = user.getUsername();
        log.info("@@@@@@ providerId : " + providerId);
        certificationService.certificationEntrepreneurValidate(req, providerId);
        return SuccessResponse.of(null);
    }
}
