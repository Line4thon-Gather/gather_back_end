package org.example.gather_back_end.creator.controller;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.creator.dto.CreateCreatorReq;
import org.example.gather_back_end.creator.service.CreatorService;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.portfolio.service.PortfolioService;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
public class CreatorController {

    private final CreatorService creatorService;
    private final PortfolioService portfolioService;

    @PostMapping()
    public SuccessResponse<?> createCreator(Authentication authentication, @RequestBody CreateCreatorReq req) {

        CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
        List<Portfolio> portfolioList = portfolioService.createPortfolio(user.getUsername(), req.portfolioList());
        creatorService.createCreator(user.getUsername(), req.introductionTitle(), req.introductionContent(), req.contactKaKaoId(), req.contactEmail(), portfolioList);
        return SuccessResponse.of(null);
    }

}
