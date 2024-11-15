package org.example.gather_back_end.user.service;

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

        // 데이터베이스에서 특정 유저 조회
        User myUser = userRepository.findByMyPage(user);

        // 전달 할 응답
        GetMyPageRes getMyPageRes;
        List<PromotionRequest> promotionRequestList = promotionRequestRepository.findAllByUser(user);
        List<GetMyPagePromotionRes> getMyPagePromotionResList = new ArrayList<>();

        // 문의 했던 홍보
        for(PromotionRequest promotionRequest : promotionRequestList) {
            getMyPagePromotionResList.add(GetMyPagePromotionRes.from(promotionRequest));
        }

        // 유저 정보 담기
        if(myUser != null) {
            getMyPageRes = new GetMyPageRes(
                    user.getProfileImgUrl(),
                    "크리에이터",
                    user.getEmail(),
                    getMyPagePromotionResList
            );
        }
        else
            getMyPageRes = new GetMyPageRes(
                    user.getProfileImgUrl(),
                    "",
                    user.getEmail(),
                    getMyPagePromotionResList
            );

        return getMyPageRes;
    }
}
