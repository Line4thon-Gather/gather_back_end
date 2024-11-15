package org.example.gather_back_end.user.service;

import org.example.gather_back_end.user.dto.GetMyPageRes;
import org.example.gather_back_end.user.dto.GetUserRes;
import org.springframework.security.core.Authentication;

public interface UserService {

    // 사용자 프로필과 이름을 가져오는 서비스
    GetUserRes getUser(Authentication authentication);

    // 마이페이지 정보 가져오기
    GetMyPageRes getMyPage(String providerId);
}
