package org.example.gather_back_end.certification.client;

import jakarta.ws.rs.QueryParam;
import org.example.gather_back_end.certification.dto.EntrepreneurClientReq;
import org.example.gather_back_end.certification.dto.EntrepreneurClientRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "entrepreneur-auth", url = "${BUSINESS_API_URL}")
public interface EntrepreneurClient {

    @PostMapping(value = "status", produces = "application/json")
    EntrepreneurClientRes getEntrepreneurClientAuthInfo(
            @RequestParam("serviceKey") String serviceKey,
            @RequestBody EntrepreneurClientReq req
    );
}
