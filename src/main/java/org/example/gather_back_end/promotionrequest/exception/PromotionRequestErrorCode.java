package org.example.gather_back_end.promotionrequest.exception;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PromotionRequestErrorCode implements BaseErrorCode {

    PROMOTION_REQUEST_NOT_FOUND_EXCEPTION(NOT_FOUND, "PROMOTION_REQUEST_404", "존재하지 않는 홍보 요청 내역입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
