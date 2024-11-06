package org.example.gather_back_end.certification.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class EmailBadRequestException extends BaseException {

    public EmailBadRequestException() {
        super(CertificateErrorCode.EMAIL_BAD_REQUEST_EXCEPTION);
    }
}
