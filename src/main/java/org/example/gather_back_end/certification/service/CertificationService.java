package org.example.gather_back_end.certification.service;

import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailRes;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateRes;

public interface CertificationService {
    CertificateUnivEmailRes certificateUnivEmail(CertificateUnivEmailReq req) throws IOException;
    CertificateUnivAuthRes certificateUnivAuth(CertificateUnivAuthReq req, String providerId) throws IOException;
    CertificationEntrepreneurValidateRes certificationEntrepreneurValidate(CertificationEntrepreneurValidateReq req, String providerId);
//    CertificationEntrepreneurValidateRes certificationEntrepreneurStatus(CertificationEntrepreneurValidateReq req);

}
