package org.example.gather_back_end.util.format;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static String formatToDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null; // 혹은 기본값 반환
        }
        // LocalDate로 변환 후 형식 지정
        LocalDate localDate = dateTime.toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}

