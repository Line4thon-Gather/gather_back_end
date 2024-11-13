package org.example.gather_back_end.promotion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorReq;
import org.example.gather_back_end.promotion.dto.creator.BestCreatorRes;
import org.example.gather_back_end.promotion.dto.promotion.PromotionRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.promotion.service.PromotionService;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    public SuccessResponse<PromotionRes> createAllPromotionInfo(
            Authentication authentication,
            @RequestBody PromotionTimelineReq req) {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = user.getUsername();
        PromotionRes res = promotionService.createAllPromotionInfo(req, providerId);
        return SuccessResponse.of(res);
    }

    @GetMapping("/test")
    public SuccessResponse<BestCreatorRes> findBestCreator(@RequestBody BestCreatorReq req) {
        promotionService.findBestCreator(req);
        return SuccessResponse.of(null);
    }
    // 사용 X
    /**
    @PostMapping("/timeline")
    public SuccessResponse<List<PromotionTimelineRes>> createPromotionTimeline(
            Authentication authentication,
            @RequestBody PromotionTimelineReq req) {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = user.getUsername();
        List<PromotionTimelineRes> res = promotionService.createPromotionStrategy(req, providerId);
        return SuccessResponse.of(res);
    }
    **/

    // 사용 X
    /**
    @PostMapping("/cost-management")
    public SuccessResponse<List<PromotionCostRes>> createPromotionCost(
            Authentication authentication,
            @RequestBody PromotionCostReq req) {
        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = user.getUsername();
        List<PromotionCostRes> res = promotionService.createPromotionCost(req);
        return SuccessResponse.of(res);
    }
    **/
}
