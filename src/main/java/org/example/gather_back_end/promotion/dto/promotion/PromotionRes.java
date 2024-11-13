package org.example.gather_back_end.promotion.dto.promotion;

import java.util.List;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineRes;

public record PromotionRes(
        List<PromotionTimelineRes> timelineList,
        List<PromotionCostRes> costList,
        List<BestCreatorRes> creatorList
) {
}
