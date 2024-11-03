package org.example.gather_back_end.certification.exception;

import org.example.gather_back_end.util.exception.BaseException;
public class EntrepreneurBadRequestException extends BaseException {

    public EntrepreneurBadRequestException() {
        super(CertificateErrorCode.ENTREPRENEUR_BAD_REQUEST_EXCEPTION);
    }
}
