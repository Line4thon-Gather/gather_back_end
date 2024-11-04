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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

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
                                    + "    \"data\": {\n"
                                    + "        \"isSuccess\": true\n"
                                    + "    }\n"
                                    + "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping
    SuccessResponse<?> createCreator(Authentication authentication, @RequestBody CreateCreatorReq req) throws IOException;

}
