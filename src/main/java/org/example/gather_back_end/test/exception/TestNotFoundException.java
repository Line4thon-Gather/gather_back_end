package org.example.gather_back_end.test.exception;

import org.example.gather_back_end.util.exception.BaseException;

public class TestNotFoundException extends BaseException {

    public TestNotFoundException() {
        super(TestErrorCode.TEST_NOT_FOUND);
    }
}
