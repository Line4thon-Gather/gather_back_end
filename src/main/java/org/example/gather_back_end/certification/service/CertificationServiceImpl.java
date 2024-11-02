package org.example.gather_back_end.certification.service;

import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    @Value("${univ_cert.api_key}")
    private String univCertApiKey;

    @Override
    public CertificationUnivEmailRes certificationUnivEmail(CertificationUnivEmailReq req) throws IOException {
        Map<String, Object> certify = UnivCert.certify(univCertApiKey, req.email(), req.univName(), true);
        boolean isSuccess = (boolean) certify.get("success");
        return CertificationUnivEmailRes.from(isSuccess);
    }

    @Override
    public CertificationUnivAuthRes certificationUnivAuth(CertificationUnivAuthReq req) throws IOException {

        // 1. 인증번호 인증
        Map<String, Object> certifyCode = UnivCert.certifyCode(univCertApiKey, req.email(), req.univName(),
                req.code());

        boolean certifyCodeResult = (boolean) certifyCode.get("success");

        if (certifyCodeResult) {

            // 2. 인증 상태 확인
            Map<String, Object> status = UnivCert.status(univCertApiKey, req.email());
            boolean statusResult = (boolean) status.get("success");

            if (statusResult) {
                return CertificationUnivAuthRes.from(true);
            }
        }

        return CertificationUnivAuthRes.from(false);
    }
}
