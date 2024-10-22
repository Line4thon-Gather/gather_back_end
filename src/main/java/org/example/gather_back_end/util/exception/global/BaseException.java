package org.example.gather_back_end.util.exception.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private final BaseErrorCode errorCode;
}
