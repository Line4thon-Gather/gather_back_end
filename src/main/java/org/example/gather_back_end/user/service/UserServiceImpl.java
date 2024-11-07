package org.example.gather_back_end.user.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.user.dto.GetUserRes;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 사용자 프로필과 이름 가져오는 서비스
    @Override
    public GetUserRes getUser(Authentication authentication) {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        User user = userRepository.getByUsername(customOAuth2User.getUsername());
        return GetUserRes.from(user);
    }
}
