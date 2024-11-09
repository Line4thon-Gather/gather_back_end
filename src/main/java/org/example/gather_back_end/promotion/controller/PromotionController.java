package org.example.gather_back_end.promotion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.promotion.dto.PromotionReq;
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

    @PostMapping
    public SuccessResponse<?> createMarketingStrategy(
//            Authentication authentication,
            @RequestBody PromotionReq req) {
        log.info("@@@@@@ RequestBody : " + req.toString());
        return null;
    }
}
