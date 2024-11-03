package org.example.gather_back_end.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 유저 종류 (대학생, 사업자)
@Getter
@AllArgsConstructor
public enum UserType {

    STUDENT("STUDENT"), // 대학생
    BUSINESS("BUSINESS"); // 사업자

    private String value;
}
