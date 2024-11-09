package org.example.gather_back_end.promotion.dto;

import java.util.List;

public record PromotionRes(
        int period,
        String category,
        List<Task> tasks
) {
}
