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
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusRes;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public CertificateUnivEmailRes certificateUnivEmail(CertificateUnivEmailReq req) throws IOException {
        Map<String, Object> certify = UnivCert.certify(univCertApiKey, req.email(), req.univName(), true);
        boolean isSuccess = (boolean) certify.get("success");
        return CertificateUnivEmailRes.from(isSuccess);
    }

    @Override
    public CertificateUnivAuthRes certificateUnivAuth(CertificateUnivAuthReq req, String providerId) throws IOException {
        boolean certifyCodeResult = verifyCertifyCode(req);
        if (certifyCodeResult) {
            boolean statusResult = checkStatus(req);

            // User 업데이트 (대학생, 최초 로그인, 인증 여부)
            User user = userRepository.getByUsername(providerId);
            User.updateStudentAuthInfo(user);

            return CertificateUnivAuthRes.from(statusResult);
        }
        return CertificateUnivAuthRes.from(false);
    }

    @Override
    public CertificationEntrepreneurRes certificationEntrepreneur(CertificationEntrepreneurReq req) {
        GetEntrepreneurStatusReq clientReq = new GetEntrepreneurStatusReq(List.of(req.number()));
        GetEntrepreneurStatusRes clientRes = entrepreneurClient.getEntrepreneurStatus(businessApiKey, clientReq);
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
