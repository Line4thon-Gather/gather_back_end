package org.example.gather_back_end.promotion.service;

import java.util.List;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostReq;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface PromotionService {

    List<PromotionTimelineRes> createPromotionStrategy (PromotionTimelineReq req);
//    List<PromotionRes> createPromotionStrategy (PromotionReq req, String providerId);

    List<PromotionCostRes> createPromotionCost(PromotionCostReq req);
}
