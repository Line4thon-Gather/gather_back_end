package org.example.gather_back_end.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.gather_back_end.user.dto.GetUserRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "User 관련", description = "유저와 관련된 API")
public interface UserControllerApi {

    @Operation(summary = "프로필 사진, 유저 닉네임 불러오기",
            description = "유저의 프로필 사진, 이름 불러오는 API 토큰만 존재하면 됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-10-22T21:35:03.755865\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": null\n"
                            + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping
    SuccessResponse<?> getUser(Authentication authentication);

    @Operation(summary = "마이페이지 불러오기",
            description = "마이페이지 불러오기, 토큰만 있으면 됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                        "timestamp": "2024-11-16T00:51:05.668577",
                                        "isSuccess": true,
                                        "code": "200",
                                        "message": "호출에 성공하였습니다.",
                                        "data": {
                                            "profileInfo": {
                                                "profileImgUrl": null,
                                                "role": "크리에이터",
                                                "email": "kms2171344@hansung.ac.kr"
                                            },
                                            "promotionInfo": [
                                                {
                                                    "createDay": "2024.11.12",
                                                    "title": "OOO 동아리 모집",
                                                    "period": 43,
                                                    "targetNumberOfPeople": 396,
                                                    "budget": 835000
                                                },
                                                {
                                                    "createDay": "2024.11.12",
                                                    "title": "OOO 동아리 모집",
                                                    "period": 43,
                                                    "targetNumberOfPeople": 396,
                                                    "budget": 835000
                                                },
                                                {
                                                    "createDay": "2024.11.13",
                                                    "title": "OOO 동아리 모집",
                                                    "period": 43,
                                                    "targetNumberOfPeople": 396,
                                                    "budget": 100000
                                                }
                                            ],
                                            "creatorInfo": [
                                                {
                                                    "nickname": "USER3322",
                                                    "availableWork": [
                                                        "PRINTS",
                                                        "SNS_POST"
                                                    ],
                                                    "introductionTitle": "dkssudd",
                                                    "startPrice": "500",
                                                    "thumbnailImgUrl": "https~"
                                                }
                                            ]
                                        }
                                    }
                                    """),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping
    SuccessResponse<?> getMyPage(Authentication authentication);
}
