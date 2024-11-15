package org.example.gather_back_end.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.user.dto.GetMyPageRes;
import org.example.gather_back_end.user.dto.GetUserRes;
import org.example.gather_back_end.user.service.UserService;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserControllerApi {

    private final UserService userService;

    // 유저 프로필 이미지 & 이름 GetMapping
    @GetMapping("/header-info")
    public SuccessResponse<?> getUser(Authentication authentication){

        GetUserRes res = userService.getUser(authentication);

        return SuccessResponse.of(res);

    }

    // 마이페이지
    @GetMapping("/my-page")
    public SuccessResponse<?> getMyPage(Authentication authentication){
        GetMyPageRes res = userService.getMyPage(authentication);
        return SuccessResponse.of(res);
    }
}
