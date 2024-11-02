package org.example.gather_back_end.portfolio.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class PortfolioNotFoundException extends BaseException {

    public PortfolioNotFoundException() {
        super(PortfolioErrorCode.PORTFOLIO_NOT_FOUND_EXCEPTION);
    }
}
