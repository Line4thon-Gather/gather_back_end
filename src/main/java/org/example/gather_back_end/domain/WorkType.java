package org.example.gather_back_end.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 작업물 종류
@Getter
@AllArgsConstructor
public enum WorkType {

    PRINTS("PRINTS"), // 인쇄물 (포스터, 배너 등)
    VIDEO("VIDEO"), // 영상 (숏폼, 미드폼, 롱폼 등)
    SNS_POST("SNS_POST"); // SNS 게시물 (카드뉴스, 피드 등)

    private String value;
}
