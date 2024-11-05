package org.example.gather_back_end.work.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.WorkType;

@Builder
public record CreateWorkReq(
        String title,
        Integer period,
        Integer startPrice,
        WorkType category
) {
}
