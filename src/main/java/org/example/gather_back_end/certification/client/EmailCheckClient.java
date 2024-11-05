package org.example.gather_back_end.certification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "email-exist-check", url = "${EMAIL_CHECK_API_URL}")
public interface EmailCheckClient {

    // 이메일 실제 존재 여부 확인 API
    @GetMapping(produces = "application/json")
    GetEmailExistCheck getEmailExistCheck(
            @RequestParam("apiKey") String apiKey,
            @RequestParam("emailAddress") String emailAddress
    );
}
