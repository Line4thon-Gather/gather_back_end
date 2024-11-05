package org.example.gather_back_end.work.dto;

import org.example.gather_back_end.domain.WorkType;

public record GetWorkRes(
        String title,
        Integer period,
        Integer startPrice,
        WorkType category
) {
}
