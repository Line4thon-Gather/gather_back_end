package org.example.gather_back_end.portfolio.exception;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PortfolioErrorCode implements BaseErrorCode {

    PORTFOLIO_NOT_FOUND_EXCEPTION(NOT_FOUND, "PORTFOLIO_404", "존재하지 않는 포트폴리오입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
