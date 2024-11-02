package org.example.gather_back_end.user.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND_EXCEPTION); }
}
