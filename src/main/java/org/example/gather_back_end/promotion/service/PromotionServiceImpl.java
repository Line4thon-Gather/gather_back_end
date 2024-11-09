package org.example.gather_back_end.promotion.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {



    @Override
    public List<PromotionRes> createPromotionStrategy(PromotionReq req) {
        return null;
    }
}
