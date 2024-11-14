package org.example.gather_back_end.creator.controller;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.bucket.service.BucketService;
import org.example.gather_back_end.creator.dto.CreateCreatorReq;
import org.example.gather_back_end.creator.dto.FilteringCreatorRes;
import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.creator.service.CreatorService;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.service.PortfolioService;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.example.gather_back_end.work.service.WorkService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
public class CreatorController implements CreatorControllerApi {

    private final CreatorService creatorService;
    private final PortfolioService portfolioService;
    private final UserRepository userRepository;
    private final WorkService workService;
    private final BucketService bucketService;

    // 크리에이터 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessResponse<?> createCreator(
            Authentication authentication,
            @RequestPart CreateCreatorReq req,  // 이름, 소개 제목, 소개글, 카카오 아이디, 이메일
            @RequestPart MultipartFile profileImgUrl, // 프로필 사진
            @RequestPart List<MultipartFile> thumbnailImgUrlList,  // 썸네일 이미지
            @RequestPart List<MultipartFile> portfolioPdfList    // 포트폴리오
            ) throws Exception {

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        User user = userRepository.getByUsername(customOAuth2User.getUsername());

        portfolioService.deletePortfolio(user.getId());
        workService.deleteWork(user.getId());

        portfolioService.createPortfolio(user.getId(), req.createPortfolioReqList(), thumbnailImgUrlList, portfolioPdfList);
        workService.createWork(user.getId(), req.createWorkReqList());

        if (profileImgUrl != null) {
            bucketService.uploadProfileImg(profileImgUrl, user.getId());
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


    // 크리에이터 상세 페이지 조회
    @GetMapping("/{nickname}")
    public SuccessResponse<?> getCreator(@PathVariable String nickname) {

        GetCreatorRes getCreatorRes = creatorService.getCreator(nickname);

        return SuccessResponse.of(getCreatorRes);
    }

    // 크리에이터 등록 초기화면 불러오기
    @GetMapping
    public SuccessResponse<?> getCreatorInfo(Authentication authentication) {

        GetCreatorRes getCreatorRes = creatorService.getCreatorInfo(authentication);

        return SuccessResponse.of(getCreatorRes);
    }

    // 크리에이터 찾기
    @GetMapping("/filtering")
    public SuccessResponse<FilteringCreatorRes> filteringCreator(
            Authentication authentication,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "category") String category,
            @RequestParam(value = "align", defaultValue = "recently") String recently
    ) {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String providerId = customOAuth2User.getUsername();
        FilteringCreatorRes res = creatorService.filteringCreator(providerId, price, category, recently);
        return SuccessResponse.of(res);
    }
}
