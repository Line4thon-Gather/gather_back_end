package org.example.gather_back_end.certification.service;

import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.certification.client.EntrepreneurClient;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailRes;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurRes;
import org.example.gather_back_end.certification.dto.EntrepreneurClientReq;
import org.example.gather_back_end.certification.dto.EntrepreneurClientRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    @Value("${univ_cert.api_key}")
    private String univCertApiKey;

    @Value("${BUSINESS_API_KEY}")
    private String businessApiKey;

    private final EntrepreneurClient entrepreneurClient;

    @Override
    public CertificateUnivEmailRes certificateUnivEmail(CertificateUnivEmailReq req) throws IOException {
        Map<String, Object> certify = UnivCert.certify(univCertApiKey, req.email(), req.univName(), true);
        boolean isSuccess = (boolean) certify.get("success");
        return CertificateUnivEmailRes.from(isSuccess);
    }

    @Override
    public CertificateUnivAuthRes certificateUnivAuth(CertificateUnivAuthReq req) throws IOException {
        boolean certifyCodeResult = verifyCertifyCode(req);
        if (certifyCodeResult) {
            boolean statusResult = checkStatus(req);
            return CertificateUnivAuthRes.from(statusResult);
        }
        return CertificateUnivAuthRes.from(false);
    }

    @Override
    public CertificationEntrepreneurRes certificationEntrepreneur(CertificationEntrepreneurReq req) {
        // EntrepreneurClientReq 객체 생성 (사업자 번호를 리스트 형태로 전달)
        EntrepreneurClientReq clientReq = new EntrepreneurClientReq(List.of(req.number()));

        // Feign 클라이언트를 통해 인증 요청
        EntrepreneurClientRes clientRes = entrepreneurClient.getEntrepreneurClientAuthInfo(businessApiKey, clientReq);

        // 응답 상태 확인: status_code가 "OK"이고 match_cnt가 0보다 큰 경우 성공으로 간주
        boolean isSuccess = clientRes != null && "OK".equals(clientRes.status_code()) && clientRes.match_cnt() > 0;

        return CertificationEntrepreneurRes.from(isSuccess);
    }

    // 인증 번호 인증
    private boolean verifyCertifyCode(CertificateUnivAuthReq req) throws IOException {
        Map<String, Object> certifyCode = UnivCert.certifyCode(univCertApiKey, req.email(), req.univName(), req.code());
        return (boolean) certifyCode.get("success");
    }

    // 인증 상태 확인
    private boolean checkStatus(CertificateUnivAuthReq req) throws IOException {
        Map<String, Object> status = UnivCert.status(univCertApiKey, req.email());
        return (boolean) status.get("success");
    }
}
