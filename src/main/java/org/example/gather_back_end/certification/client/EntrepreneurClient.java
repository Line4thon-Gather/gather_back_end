package org.example.gather_back_end.certification.client;

import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "entrepreneur-auth", url = "${BUSINESS_API_URL}")
public interface EntrepreneurClient {

    @PostMapping(value = "status", produces = "application/json")
    EntrepreneurClientRes getEntrepreneurClientAuthInfo(
            @QueryParam("${BUSINESS_API_KEY}") String serviceKey,
            @RequestBody  EntrepreneurClientReq req
    );
}
