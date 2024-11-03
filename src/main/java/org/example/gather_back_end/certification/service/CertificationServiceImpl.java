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
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateRes;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusRes;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateRes;
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

    // 사업자 등록 검증
    @Override
    public CertificationEntrepreneurValidateRes certificationEntrepreneurValidate(
            CertificationEntrepreneurValidateReq req, String providerId) {

        // 요청 객체를 외부 API 호출 스펙에 맞게 변환
        GetEntrepreneurValidateReq externalApiReq = changeReqDtoReqToExternalApiSpecDtoReq(req);

        // 외부 API 호출하여 유효성 결과 받기
        GetEntrepreneurValidateRes validResult = fetchEntrepreneurValidationResult(externalApiReq);

        // 유효성 검사 코드
        String resCode = validResult.data().getFirst().valid();
        if (!"01".equals(resCode)) {
            return CertificationEntrepreneurValidateRes.from(false, "등록된 사업자 정보가 일치하지 않음");
        }

        // 사업자 상태 검사
        boolean isSuccess = checkEntrepreneurStatus(req);
        if (isSuccess) {
            updateUserEntrepreneurAuthInfo(providerId);
            return CertificationEntrepreneurValidateRes.from(true, "사업자 등록 인증 성공");
        }

        return CertificationEntrepreneurValidateRes.from(false, "등록된 사업자 정보가 일치하지 않음");
    }

//    @Override
//    public CertificationEntrepreneurValidateRes certificationEntrepreneurValidate(
//            CertificationEntrepreneurValidateReq req, String providerId) {
//
//        // req 객체를 외부 API 호출 스펙에 맞게 변환
//        GetEntrepreneurValidateReq externalApiReq = changeReqDtoReqToExternalApiSpecDtoReq(req);
//
//        // 외부 API 호출하여 결과 받기
//        GetEntrepreneurValidateRes validResult = entrepreneurClient.getEntrepreneurValidate(
//                businessApiKey,
//                externalApiReq
//        );
//
//        // resCode 01 : 사업자 존재
//        // resCode 02 : 사업자 존재하지 않음
//        String resCode = validResult.data().get(0).valid();
//
//        if (resCode.equals("01")) { // 사업자 존재
//            GetEntrepreneurStatusRes statusResult = entrepreneurClient.getEntrepreneurStatus
//                (
//                    businessApiKey,
//                    GetEntrepreneurStatusReq.of(req.b_no()
//                )
//            );
//
//            boolean isSuccess = statusResult != null && "OK".equals(statusResult.status_code()) && statusResult.match_cnt() > 0;
//
//            if (isSuccess) {
//
//                // 사용자 Repository 업데이트
//                User user = userRepository.getByUsername(providerId);
//                User.updateEntrepreneurAuthInfo(user);
//
//                return CertificationEntrepreneurValidateRes.from(true, "사업자 등록 인증 성공");
//            } else {
//                return CertificationEntrepreneurValidateRes.from(false, "등록된 사업자 정보가 일치하지 않음");
//            }
//
//        } else { // 사업자 존재 X
//            return CertificationEntrepreneurValidateRes.from(false, "등록된 사업자 정보가 일치하지 않음");
//        }
//    }

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
