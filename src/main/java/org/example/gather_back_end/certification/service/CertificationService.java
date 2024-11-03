package org.example.gather_back_end.certification.service;

import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailRes;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CertificationService {
    CertificateUnivEmailRes certificateUnivEmail(CertificateUnivEmailReq req) throws IOException;
    CertificateUnivAuthRes certificateUnivAuth(CertificateUnivAuthReq req, String providerId) throws IOException;
    CertificationEntrepreneurRes certificationEntrepreneur(CertificationEntrepreneurReq req);
}
