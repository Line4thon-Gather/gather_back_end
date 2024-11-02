package org.example.gather_back_end.user.exception;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND_EXCEPTION(NOT_FOUND, "USER_404", "존재하지 회원입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
