package org.example.gather_back_end.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "테스트 용으로 만든 API", description = "테스트용")
public interface TestControllerApi {

    @Operation(summary = "[GET 테스트] 성공 테스트용 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-10-22T21:35:03.755865\",\n"
                                + "    \"isSuccess\": true,\n"
                                + "    \"code\": \"200\",\n"
                                + "    \"message\": \"호출에 성공하였습니다.\",\n"
                                + "    \"data\": \"test\"\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
    })
    @GetMapping
    SuccessResponse<String> testSuccess();

    @Operation(summary = "[GET 테스트] 성공 테스트용 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n"
                                + "    \"timestamp\": \"2024-10-22T21:34:54.964205\",\n"
                                + "    \"isSuccess\": false,\n"
                                + "    \"code\": \"TEST_404_1\",\n"
                                + "    \"message\": \"테스트를 찾을 수 없습니다.\",\n"
                                + "    \"httpStatus\": 404\n"
                                + "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
    })
    @GetMapping
    SuccessResponse<String> testException();
}
