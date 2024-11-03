package org.example.gather_back_end.certification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.example.gather_back_end.certification.dto.CertificateUnivAuthReq;
import org.example.gather_back_end.certification.dto.CertificateUnivEmailReq;
import org.example.gather_back_end.certification.dto.CertificationEntrepreneurValidateReq;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
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
    SuccessResponse<?> certificateUnivEmail(@RequestBody CertificateUnivEmailReq req) throws IOException;

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
    SuccessResponse<?> certificateUnivAuth(Authentication authentication, @RequestBody CertificateUnivAuthReq req) throws IOException;

    @Operation(summary = "사업자 인증",
                description = "사업자 인증 성공한 경우, data의 isSuccess 필드가 true\n"
                                + "사업자 인증 실패 시, data의 isSuccess 필드가 false"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증 성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-03T15:44:24.484124\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": null\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "인증 실패",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-03T15:44:24.484124\",\n"
                                + "    \"isSuccess\": false,\n"
                                + "    \"code\": \"ENTREPRENEUR_BAD_REQUEST_400\",\n"
                                + "    \"message\": \"사업자 등록 정보가 일치하지 않음\",\n"
                                + "    \"httpStatus\": 400\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class))
            )
    })
    @PostMapping
    SuccessResponse<?> certificationEntrepreneur(Authentication authentication, @RequestBody CertificationEntrepreneurValidateReq req);

}
