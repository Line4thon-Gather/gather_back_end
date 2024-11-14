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
    AUTH_NUMBER_NOT_MATCH_BAD_REQUEST(BAD_REQUEST, "AUTH_NUMBER_NOT_MATCH_BAD_REQUEST_400", "이메일로 전송된 코드와 인증번호가 일치하지 않음"),
    UNIV_NOT_FOUND_EXCEPTION(NOT_FOUND, "UNIV_NOT_FOUND_404", "올바르지 않은 대학명"),
    EMAIL_BAD_REQUEST_EXCEPTION(BAD_REQUEST, "EMAIL_BAD_REQUEST_400", "존재하지 않는 이메일 주소"),
    EMAIL_CLEAR_BAD_REQUEST_EXCEPTION(BAD_REQUEST, "EMAIL_CLEAR_BAD_REQUEST_400", "이메일 인증 초기화 실패");

    private final int httpStatus;
    private final String code;
    private final String message;
}
