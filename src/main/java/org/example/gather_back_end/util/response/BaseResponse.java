package org.example.gather_back_end.util.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.gather_back_end.util.exception.global.BaseErrorCode;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseResponse {

    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    private final Boolean isSuccess;
    private final String code;
    private final String message;

    public static BaseResponse of(Boolean isSuccess, BaseErrorCode errorCode) {
        return new BaseResponse(isSuccess, errorCode.getCode(), errorCode.getMessage());
    }

    public static BaseResponse of(Boolean isSuccess, BaseErrorCode errorCode, String message) {
        return new BaseResponse(isSuccess, errorCode.getCode(), message);
    }

    public static BaseResponse of(Boolean isSuccess, String code, String message) {
        return new BaseResponse(isSuccess, code, message);
    }
}
