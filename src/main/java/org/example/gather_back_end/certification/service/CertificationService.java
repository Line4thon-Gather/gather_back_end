package org.example.gather_back_end.certification.service;

import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;

public interface CertificationService {
    void certificateUnivEmail(CertificateUnivEmailReq req) throws IOException;
    void certificateUnivAuth(CertificateUnivAuthReq req, String providerId) throws IOException;
    void certificationEntrepreneurValidate(CertificationEntrepreneurValidateReq req, String providerId);

}
