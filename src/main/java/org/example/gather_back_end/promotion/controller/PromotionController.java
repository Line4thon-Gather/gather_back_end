package org.example.gather_back_end.promotion.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;
import org.example.gather_back_end.promotion.service.PromotionService;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
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
    public SuccessResponse<List<PromotionRes>> createPromotionStrategy(
            // TODO: 로그인 완성되면 Authentication 추가하기
            // TODO: DB에 req 정보 저장하기
//            Authentication authentication,
            @RequestBody PromotionReq req) {
//        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
//        String providerId = user.getUsername();
//        List<PromotionRes> res = promotionService.createPromotionStrategy(req, providerId);
        List<PromotionRes> res = promotionService.createPromotionStrategy(req);
        return SuccessResponse.of(res);
    }
}
