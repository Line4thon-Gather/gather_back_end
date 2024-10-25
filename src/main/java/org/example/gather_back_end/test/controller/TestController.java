package org.example.gather_back_end.test.controller;

import org.example.gather_back_end.test.exception.TestNotFoundException;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController implements TestControllerApi {

    @GetMapping("/success")
    public SuccessResponse<String> testSuccess(){
        return SuccessResponse.of("test");
    }

    @GetMapping("/exception")
    public SuccessResponse<String> testException() {
        throw new TestNotFoundException();
    }

    @GetMapping("/jwt-test")
    public SuccessResponse<String> testJwt(Authentication authentication) {
        if (authentication == null) {
            return SuccessResponse.of("token is null");
        }
        else{
            return SuccessResponse.of(authentication.getName(),"알 수 없는 에러");
        }
    }
}
