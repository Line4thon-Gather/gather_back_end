package org.example.gather_back_end.util.exception.custom;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.global.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum CustomErrorCode implements BaseErrorCode {

    TEST_NOT_FOUND(NOT_FOUND, "TEST_404_1", "테스트를 찾을 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
