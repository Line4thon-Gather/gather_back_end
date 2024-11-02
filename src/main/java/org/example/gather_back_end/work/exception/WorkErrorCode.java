package org.example.gather_back_end.work.exception;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum WorkErrorCode implements BaseErrorCode {

    WORK_NOT_FOUND_EXCEPTION(NOT_FOUND, "WORK_404", "존재하지 않는 작업입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
