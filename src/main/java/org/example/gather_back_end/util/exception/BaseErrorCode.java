package org.example.gather_back_end.util.exception;

public interface BaseErrorCode {

    String getCode();
    String getMessage();
    int getHttpStatus();
}
