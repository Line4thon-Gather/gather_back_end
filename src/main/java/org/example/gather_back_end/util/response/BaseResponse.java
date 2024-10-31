package org.example.gather_back_end.util.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseResponse {

    @Schema(description = "API 호출 일시", example = "2024-10-30T15:38:12.43483271")
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    @Schema(description = "API 호출 성공 여부", example = "true")
    private final Boolean isSuccess;

    @Schema(description = "응답 코드", example = "200")
    private final String code;

    @Schema(description = "응답 메시지", example = "호출에 성공하였습니다.")
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
