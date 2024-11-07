package org.example.gather_back_end.creator.controller;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.bucket.service.BucketService;
import org.example.gather_back_end.creator.dto.CreateCreatorReq;
import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.creator.service.CreatorService;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.service.PortfolioService;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.example.gather_back_end.work.service.WorkService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
public class CreatorController implements CreatorControllerApi {

    private final CreatorService creatorService;
    private final PortfolioService portfolioService;
    private final UserRepository userRepository;
    private final WorkService workService;
    private final BucketService bucketService;

    @PostMapping()
    public SuccessResponse<?> createCreator(Authentication authentication, @RequestBody CreateCreatorReq req) throws Exception {

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        User user = userRepository.getByUsername(customOAuth2User.getUsername());

        portfolioService.deletePortfolio(user.getId());
        workService.deleteWork(user.getId());

        portfolioService.createPortfolio(user.getId(), req.createPortfolioReqList());
        workService.createWork(user.getId(), req.createWorkReqList());

        if (req.profileImgUrl() != null) {
            bucketService.uploadProfileImg(req.profileImgUrl(), user.getId());
        }

        creatorService.createCreator(
                user.getId(),
                req.nickname(),
                req.introductionTitle(),
                req.introductionContent(),
                req.contactKaKaoId(),
                req.contactEmail()
        );

        return SuccessResponse.of(null);
    }

    @GetMapping("/{nickname}")
    public SuccessResponse<?> getCreator(@PathVariable String nickname) {

        GetCreatorRes getCreatorRes = creatorService.getCreator(nickname);

        return SuccessResponse.of(getCreatorRes);
    }

}
