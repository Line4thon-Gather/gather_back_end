package org.example.gather_back_end.user.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.PromotionRequestRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.user.dto.GetMyPagePromotionRes;
import org.example.gather_back_end.user.dto.GetMyPageRes;
import org.example.gather_back_end.user.dto.GetUserRes;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PromotionRequestRepository promotionRequestRepository;

    // 사용자 프로필과 이름 가져오는 서비스
    @Override
    public GetUserRes getUser(Authentication authentication) {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        User user = userRepository.getByUsername(customOAuth2User.getUsername());
        return GetUserRes.from(user);
    }

    @Override
    public GetMyPageRes getMyPage(Authentication authentication) {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        // 유저 정보
        User user = userRepository.getByUsername(customOAuth2User.getUsername());

        // 전달 할 응답
        GetMyPageRes getMyPageRes;
        List<PromotionRequest> promotionRequestList = promotionRequestRepository.findAllByUser(user);
        List<GetMyPagePromotionRes> getMyPagePromotionResList = new ArrayList<>();

        // 문의 했던 홍보
        for(PromotionRequest promotionRequest : promotionRequestList) {
            getMyPagePromotionResList.add(GetMyPagePromotionRes.from(promotionRequest));
        }

        // 유저 정보 담기
        boolean isUserCreator = userRepository.isUserCreator(user.getId());
        getMyPageRes = new GetMyPageRes(
                user.getProfileImgUrl(),
                isUserCreator ? "크리에이터" : null,
                user.getEmail(),
                getMyPagePromotionResList
        );

        return getMyPageRes;
    }
}
