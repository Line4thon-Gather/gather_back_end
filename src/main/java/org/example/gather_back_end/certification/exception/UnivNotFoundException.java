package org.example.gather_back_end.certification.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class UnivNotFoundException extends BaseException {

    public UnivNotFoundException() {
        super(CertificateErrorCode.UNIV_NOT_FOUND_EXCEPTION);
    }
}
