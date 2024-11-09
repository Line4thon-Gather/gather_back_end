package org.example.gather_back_end.promotion.service;

import java.util.List;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;

public interface PromotionService {

    List<PromotionRes> createPromotionStrategy (PromotionReq req);
}
