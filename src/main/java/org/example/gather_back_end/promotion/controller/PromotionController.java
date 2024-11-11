package org.example.gather_back_end.promotion.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostReq;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineRes;
import org.example.gather_back_end.promotion.service.PromotionService;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotion")
public class PromotionController implements PromotionControllerApi {

    private final PromotionService promotionService;

    @PostMapping("/timeline")
    public SuccessResponse<List<PromotionTimelineRes>> createPromotionTimeline(
            // TODO: 로그인 완성되면 Authentication 추가하기
            // TODO: DB에 req 정보 저장하기
//            Authentication authentication,
            @RequestBody PromotionTimelineReq req) {
//        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
//        String providerId = user.getUsername();
//        List<PromotionRes> res = promotionService.createPromotionStrategy(req, providerId);
        List<PromotionTimelineRes> res = promotionService.createPromotionStrategy(req);
        return SuccessResponse.of(res);
    }

    @PostMapping("/cost-management")
    public SuccessResponse<List<PromotionCostRes>> createPromotionCost(@RequestBody PromotionCostReq req) {
        List<PromotionCostRes> res = promotionService.createPromotionCost(req);
        return SuccessResponse.of(res);
    }
}
