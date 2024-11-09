package org.example.gather_back_end.promotion.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;
import org.example.gather_back_end.promotion.service.PromotionService;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/marketing")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public SuccessResponse<?> createPromotionStrategy(
//            Authentication authentication,
            @RequestBody PromotionReq req) {
        log.info("@@@@@@ RequestBody : " + req.toString());
        List<PromotionRes> res = promotionService.createPromotionStrategy(req);
        return SuccessResponse.of(res);
    }
}
