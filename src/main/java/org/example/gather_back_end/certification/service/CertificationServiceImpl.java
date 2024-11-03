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
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusRes;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateRes;
import org.example.gather_back_end.certification.exception.EntrepreneurBadRequestException;
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

    @Value("${business.api_key}")
    private String businessApiKey;

    private final EntrepreneurClient entrepreneurClient;
    private final UserRepository userRepository;

    @Override
    public void certificateUnivEmail(CertificateUnivEmailReq req) throws IOException {
        Map<String, Object> certify = UnivCert.certify(univCertApiKey, req.email(), req.univName(), true);
        boolean isSuccess = (boolean) certify.get("success");
        log.info("@@@@@@ 이메일 인증번호 전송 isSuccess : " + isSuccess);
    }

    @Override
    public void certificateUnivAuth(CertificateUnivAuthReq req, String providerId) throws IOException {
        boolean certifyCodeResult = verifyCertifyCode(req);
        if (certifyCodeResult) {
            boolean statusResult = checkStatus(req);

            // User 업데이트 (대학생, 최초 로그인, 인증 여부)
            User user = userRepository.getByUsername(providerId);
            User.updateStudentAuthInfo(user);
            return;
        }
        return CertificateUnivAuthRes.from(false);
    }

    // 사업자 등록 검증
    @Override
    public void certificationEntrepreneurValidate(
            CertificationEntrepreneurValidateReq req, String providerId) {

        // 요청 객체를 외부 API 호출 스펙에 맞게 변환
        GetEntrepreneurValidateReq externalApiReq = changeReqDtoReqToExternalApiSpecDtoReq(req);

        // 외부 API 호출하여 유효성 결과 받기
        GetEntrepreneurValidateRes validResult = fetchEntrepreneurValidationResult(externalApiReq);
        log.info("@@@@@@@@@ 유효성 결과" + validResult.toString());

        // 유효성 검사 코드
        String resCode = validResult.data().getFirst().valid();
        if (!"01".equals(resCode)) {
            log.info("[CertificationServiceImpl][certificationEntrepreneurValidate] : 유효성 검사 실패");
            throw new EntrepreneurBadRequestException();
        }

        // 사업자 상태 검사
        boolean isSuccess = checkEntrepreneurStatus(req);
        if (isSuccess) {
            log.info("[CertificationServiceImpl][certificationEntrepreneurValidate] : 사업자 번호 검사 성공");
            log.info("[CertificationServiceImpl][certificationEntrepreneurValidate] : 사용자 정보 업데이트");
            updateUserEntrepreneurAuthInfo(providerId);
            return;
        }

        log.info("[CertificationServiceImpl][certificationEntrepreneurValidate] : 사업자 상태 검사 실패");
        throw new EntrepreneurBadRequestException();
    }

    private static GetEntrepreneurValidateReq changeReqDtoReqToExternalApiSpecDtoReq(CertificationEntrepreneurValidateReq req) {
        List<CertificationEntrepreneurValidateReq> certificationReqs = List.of(
            new CertificationEntrepreneurValidateReq(
                req.b_no(),
                req.start_dt(),
                req.p_nm()
            )
        );
        return GetEntrepreneurValidateReq.from(certificationReqs);
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

    // 유효성 검사 결과 (외부 API 호출)
    private GetEntrepreneurValidateRes fetchEntrepreneurValidationResult(GetEntrepreneurValidateReq externalApiReq) {
        return entrepreneurClient.getEntrepreneurValidate(businessApiKey, externalApiReq);
    }

    // 사업자 상태 확인 (외부 API 호출)
    private boolean checkEntrepreneurStatus(CertificationEntrepreneurValidateReq req) {
        GetEntrepreneurStatusRes statusResult = entrepreneurClient.getEntrepreneurStatus(
                businessApiKey,
                GetEntrepreneurStatusReq.of(req.b_no())
        );
        return statusResult != null && "OK".equals(statusResult.status_code()) && statusResult.match_cnt() > 0;
    }

    // 사용자 정보 업데이트
    private void updateUserEntrepreneurAuthInfo(String providerId) {
        User user = userRepository.getByUsername(providerId);
        User.updateEntrepreneurAuthInfo(user);
    }
}
