package org.example.gather_back_end.certification.service;

import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public CertificationUnivEmailRes certificationUnivEmail(CertificationUnivEmailReq req) throws IOException {
        Map<String, Object> certify = UnivCert.certify(univCertApiKey, req.email(), req.univName(), true);
        boolean isSuccess = (boolean) certify.get("success");
        return CertificationUnivEmailRes.of(isSuccess);
    }
}
