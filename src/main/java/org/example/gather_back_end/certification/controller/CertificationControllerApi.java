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
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "인증 관련", description = "유저 인증과 관련된 API")
public interface CertificationControllerApi {

    @Operation(summary = "이메일 인증번호 전송",
                description = "이 API에서 200 OK가 떴다면 이메일로 인증번호가 전송되었다는 것을 의미함"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "인증번호 전송 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n"
                                            + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                            + "    \"isSuccess\": true,\n"
                                            + "    \"code\": \"200\",\n"
                                            + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                            + "    \"data\": null\n"
                                            + "}"
                            ),
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "존재하지 않는 이메일로 인증번호 전송하려고 하면 발생하는 오류",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n"
                                            + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                            + "    \"isSuccess\": false,\n"
                                            + "    \"code\": \"EMAIL_BAD_REQUEST_EXCEPTION\",\n"
                                            + "    \"message\": \"존재하지 않는 이메일 주소\",\n"
                                            + "    \"httpStatus\": 400\n"
                                            + "}"
                            ),
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "올바르지 않은 대학명",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n"
                                            + "    \"timestamp\": \"2024-11-03T18:08:57.452930427\",\n"
                                            + "    \"isSuccess\": false,\n"
                                            + "    \"code\": \"UNIV_NOT_FOUND_EXCEPTION\",\n"
                                            + "    \"message\": \"올바르지 않은 대학명\",\n"
                                            + "    \"httpStatus\": 404\n"
                                            + "}"
                            ),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping
    SuccessResponse<?> certificateUnivEmail(@RequestBody CertificateUnivEmailReq req) throws IOException;

    @Operation(summary = "이메일 인증번호 인증")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "인증번호 인증 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\n"
                                            + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                            + "    \"isSuccess\": true,\n"
                                            + "    \"code\": \"200\",\n"
                                            + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                            + "    \"data\": null\n"
                                            + "}"
                            ),
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "인증번호 인증 실패",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-03T15:44:24.484124\",\n"
                                + "    \"isSuccess\": false,\n"
                                + "    \"code\": \"AUTH_NUMBER_NOT_MATCH_BAD_REQUEST_400\",\n"
                                + "    \"message\": \"이메일로 전송된 코드와 인증번호가 일치하지 않음\",\n"
                                + "    \"httpStatus\": 400\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class))
            )
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
