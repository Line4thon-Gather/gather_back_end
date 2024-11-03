package org.example.gather_back_end.certification.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class AuthNumberNotMatchBadRequestException extends BaseException {

    public AuthNumberNotMatchBadRequestException() {
        super(CertificateErrorCode.AUTH_NUMBER_NOT_MATCH_BAD_REQUEST);
    }
}
