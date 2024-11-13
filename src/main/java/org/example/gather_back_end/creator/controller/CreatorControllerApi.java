package org.example.gather_back_end.creator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.gather_back_end.creator.dto.CreateCreatorReq;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Tag(name = "크리에이터 프로필 관련", description = "크리에이터 프로필 관련된 API")
public interface CreatorControllerApi {

    @Operation(summary = "크리에이터 등록 완료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "크리에이터 등록 완료",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                    + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                    + "    \"isSuccess\": true,\n"
                                    + "    \"code\": \"200\",\n"
                                    + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                    + "    \"data\": \"null"
                                    + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<?> createCreator(
            Authentication authentication,
            @RequestPart CreateCreatorReq req,  // 이름, 소개 제목, 소개글, 카카오 아이디, 이메일
            @RequestPart MultipartFile profileImgUrl, // 프로필 사진
            @RequestPart List<MultipartFile> thumbnailImgUrlList,  // 썸네일 이미지
            @RequestPart List<MultipartFile> portfolioPdfList    // 포트폴리오
    ) throws Exception;

    @Operation(summary = "크리에이터 상세 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "크리에이터 상세 페이지 조회 nickname = 크리에이터명",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                    + "    \"timestamp\": \"2024-11-03T05:07:47.704694\",\n"
                                    + "    \"isSuccess\": true,\n"
                                    + "    \"code\": \"200\",\n"
                                    + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                    + "    \"data\": \"null"
                                    + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping
    SuccessResponse<?> getCreator(@PathVariable String nickname);
}
