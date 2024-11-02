package org.example.gather_back_end.promotionrequest.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class PromotionRequestNotFoundException extends BaseException {

    public PromotionRequestNotFoundException() {
        super(PromotionRequestErrorCode.PROMOTION_REQUEST_NOT_FOUND_EXCEPTION);
    }
}
