package org.example.gather_back_end.promotionresult.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class PromotionResultNotFoundException extends BaseException {

    public PromotionResultNotFoundException() {
        super(PromotionResultErrorCode.PROMOTION_RESULT_NOT_FOUND_EXCEPTION);
    }

}
