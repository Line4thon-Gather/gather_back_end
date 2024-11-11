package org.example.gather_back_end.promotion.service;

import java.util.List;
import org.example.gather_back_end.promotion.dto.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.PromotionTimelineRes;

public interface PromotionService {

    List<PromotionTimelineRes> createPromotionStrategy (PromotionTimelineReq req);
//    List<PromotionRes> createPromotionStrategy (PromotionReq req, String providerId);
}
