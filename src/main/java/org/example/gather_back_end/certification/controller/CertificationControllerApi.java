package org.example.gather_back_end.certification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificationUnivAuthRes;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationUnivEmailRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "인증 관련", description = "유저 인증과 관련된 API")
public interface CertificationControllerApi {

    @Operation(summary = "이메일 인증번호 전송")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호 전송 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": {\n"
                                + "        \"isSuccess\": true\n"
                                + "    }\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<CertificationUnivEmailRes> certificationUnivEmail(@RequestBody CertificationUnivEmailReq req) throws IOException;

    @Operation(summary = "이메일 인증번호 인증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": {\n"
                                + "        \"isSuccess\": true\n"
                                + "    }\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<CertificationUnivAuthRes> certificationUnivAuth(@RequestBody CertificationUnivAuthReq req) throws IOException;
}