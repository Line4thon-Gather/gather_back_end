package org.example.gather_back_end.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.gather_back_end.user.dto.UploadProfileImgRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User 관련", description = "유저와 관련된 API")
public interface UserControllerApi {

    @Operation(summary = "프로필 사진 업로드", description = "유저의 프로필 사진을 업로드하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-10-22T21:35:03.755865\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"프로필 사진이 성공적으로 업로드되었습니다.\",\n"
                                + "    \"data\": null\n"
                            + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping("/{userId}/profileImg")
    SuccessResponse<UploadProfileImgRes> uploadProfileImg(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws Exception;
}
