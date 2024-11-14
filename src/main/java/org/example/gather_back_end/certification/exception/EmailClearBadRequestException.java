package org.example.gather_back_end.certification.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class EmailClearBadRequestException extends BaseException {

    public EmailClearBadRequestException() {
        super(CertificateErrorCode.EMAIL_CLEAR_BAD_REQUEST_EXCEPTION);
    }
}
