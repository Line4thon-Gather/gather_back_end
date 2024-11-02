package org.example.gather_back_end.work.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class WorkNotFoundException extends BaseException {

    public WorkNotFoundException() {
        super(WorkErrorCode.WORK_NOT_FOUND_EXCEPTION);
    }
}
