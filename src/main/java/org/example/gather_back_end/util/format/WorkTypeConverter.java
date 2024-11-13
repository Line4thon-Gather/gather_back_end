package org.example.gather_back_end.util.format;

import java.util.Map;
import org.example.gather_back_end.domain.WorkType;

public class WorkTypeConverter {

    private static final Map<WorkType, String> workTypeToKoreanMap = Map.of(
            WorkType.PRINTS, "인쇄물",
            WorkType.SNS_POST, "SNS 게시물",
            WorkType.VIDEO, "영상"
    );

    // WorkType을 한글명으로 변환하는 메서드
    public static String toKorean(WorkType workType) {
        return workTypeToKoreanMap.getOrDefault(workType, workType.name());
    }
}
