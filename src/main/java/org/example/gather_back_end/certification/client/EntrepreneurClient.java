package org.example.gather_back_end.certification.client;

import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurStatusRes;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateReq;
import org.example.gather_back_end.certification.dto.GetEntrepreneurValidateRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "entrepreneur-auth", url = "${BUSINESS_API_URL}")
public interface EntrepreneurClient {

    @PostMapping(value = "status", produces = "application/json")
    GetEntrepreneurStatusRes getEntrepreneurStatus(
            @RequestParam("serviceKey") String serviceKey,
            @RequestBody GetEntrepreneurStatusReq req
    );

    @PostMapping(value = "validate", produces = "application/json")
    GetEntrepreneurValidateRes getEntrepreneurValidate(
            @RequestParam("serviceKey") String serviceKey,
            @RequestBody GetEntrepreneurValidateReq req
    );
}
