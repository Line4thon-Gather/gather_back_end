package org.example.gather_back_end.util.exception.global;

public interface BaseErrorCode {

    String getCode();
    String getMessage();
    int getHttpStatus();
}
