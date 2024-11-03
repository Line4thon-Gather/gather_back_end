package org.example.gather_back_end.certification.exception;

import static org.example.gather_back_end.util.constant.StaticValue.BAD_REQUEST;
import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum CertificateErrorCode implements BaseErrorCode {

    ENTREPRENEUR_BAD_REQUEST_EXCEPTION(BAD_REQUEST, "ENTREPRENEUR_BAD_REQUEST_400", "사업자 등록 정보가 일치하지 않음"),
    AUTH_NUMBER_NOT_MATCH_BAD_REQUEST(BAD_REQUEST, "AUTH_NUMBER_NOT_MATCH_BAD_REQUEST_400", "이메일로 전송된 코드와 인증번호가 일치하지 않음");

    private final int httpStatus;
    private final String code;
    private final String message;
}
