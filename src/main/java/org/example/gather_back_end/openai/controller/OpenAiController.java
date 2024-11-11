package org.example.gather_back_end.openai.controller;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.openai.dto.CustomOpenAiClientResponse;
import org.example.gather_back_end.openai.service.OpenAiService;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/openai")
public class OpenAiController implements OpenAiControllerApi {

    private final OpenAiService openAiService;

    @PostMapping("/test")
    public SuccessResponse<CustomOpenAiClientResponse> requestToOpenAi(
//            Authentication authentication,
            @RequestBody PromotionTimelineReq request
    ) {
//        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
//        if (customOAuth2User == null) {
//            return null;
//        }

        CustomOpenAiClientResponse response = openAiService.getAboutTimelineOpenAiResponse(request);
        return SuccessResponse.of(response);
    }

    @PostMapping("/test2")
    public String test(Authentication authentication,@RequestBody String test) {
        if (authentication == null) {
            return "null입니다.";
        }
        else {
            return test;
        }
    }
}
