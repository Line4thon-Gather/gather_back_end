package org.example.gather_back_end.view.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class UsersViewRecordNotFoundException extends BaseException {

    public UsersViewRecordNotFoundException() {
        super(ViewRecordErrorCode.USERS_VIEW_RECORD_NOT_FOUND);
    }
}
