package org.example.gather_back_end.view.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class ViewRecordNotFoundException extends BaseException {

    public ViewRecordNotFoundException() {
        super(ViewRecordErrorCode.VIEW_RECORD_NOT_FOUND);
    }
}
