package org.example.gather_back_end.promotion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.example.gather_back_end.promotion.dto.PromotionReq;
import org.example.gather_back_end.promotion.dto.PromotionRes;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "홍보 타임라인 관련", description = "타임라인 생성, 비용 관리")
public interface PromotionControllerApi {

    @Operation(summary = "타임라인 정보 생성")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "호출 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-11-09T22:47:19.749914\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": [\n"
                                + "        {\n"
                                + "            \"period\": 43,\n"
                                + "            \"category\": \"PRINTS\",\n"
                                + "            \"tasks\": [\n"
                                + "                {\n"
                                + "                    \"name\": \"기획\",\n"
                                + "                    \"start\": 0,\n"
                                + "                    \"end\": 1\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"디자인\",\n"
                                + "                    \"start\": 1,\n"
                                + "                    \"end\": 4\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"출력\",\n"
                                + "                    \"start\": 4,\n"
                                + "                    \"end\": 5\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시요청_캠퍼스픽\",\n"
                                + "                    \"start\": 5,\n"
                                + "                    \"end\": 6\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"승인기간_캠퍼스픽\",\n"
                                + "                    \"start\": 6,\n"
                                + "                    \"end\": 8\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시_요즘것들\",\n"
                                + "                    \"start\": 5,\n"
                                + "                    \"end\": 43\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시_인스타그램\",\n"
                                + "                    \"start\": 5,\n"
                                + "                    \"end\": 43\n"
                                + "                }\n"
                                + "            ]\n"
                                + "        }\n"
                                + "    ]\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            )

    })
    @PostMapping
    SuccessResponse<List<PromotionRes>> createPromotionStrategy(
            @RequestBody PromotionReq req
    );
}
