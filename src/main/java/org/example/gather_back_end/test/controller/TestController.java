package org.example.gather_back_end.test.controller;

import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public SuccessResponse<String> test(){
        return SuccessResponse.of("test");
    }
}
