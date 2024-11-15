package org.example.gather_back_end.view.exception;

import static org.example.gather_back_end.util.constant.StaticValue.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.gather_back_end.util.exception.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ViewRecordErrorCode implements BaseErrorCode {

    VIEW_RECORD_NOT_FOUND(NOT_FOUND, "VIEW_RECORD_404_1", "ViewRecord 엔티티를 찾을 수 없습니다."),
    USERS_VIEW_RECORD_NOT_FOUND(NOT_FOUND, "USERS_VIEW_RECORD_404_1", "UsersViewRecord 엔티티를 찾을 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
