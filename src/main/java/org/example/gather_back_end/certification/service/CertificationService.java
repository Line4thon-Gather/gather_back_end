package org.example.gather_back_end.certification.service;

import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailRes;

public interface CertificationService {
    CertificationUnivEmailRes certificationUnivEmail(CertificationUnivEmailReq req) throws IOException;
}
