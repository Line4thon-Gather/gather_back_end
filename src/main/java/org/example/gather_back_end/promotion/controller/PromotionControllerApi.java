package org.example.gather_back_end.promotion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.example.gather_back_end.promotion.dto.PromotionTimelineReq;
import org.example.gather_back_end.promotion.dto.PromotionTimelineRes;
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
                                + "    \"timestamp\": \"2024-11-11T17:30:37.097401\",\n"
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
                                + "                    \"end\": 2,\n"
                                + "                    \"tip\": \"단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"디자인\",\n"
                                + "                    \"start\": 2,\n"
                                + "                    \"end\": 10,\n"
                                + "                    \"tip\": \"단체의 색깔과 캐릭터를 정하고, 원하는 내용을 간결하게 정리해 디자이너와 원활히 소통하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"출력\",\n"
                                + "                    \"start\": 10,\n"
                                + "                    \"end\": 11,\n"
                                + "                    \"tip\": \"시간적 여유가 있다면 Bizhows로, 급하다면 학교나 인쇄소에서 사이즈를 미리 확인 후 인쇄하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시요청_캠퍼스픽\",\n"
                                + "                    \"start\": 11,\n"
                                + "                    \"end\": 12,\n"
                                + "                    \"tip\": \"접근성 높은 캠퍼스픽에 포스터를 준비해 효과적으로 홍보하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"승인기간_캠퍼스픽\",\n"
                                + "                    \"start\": 12,\n"
                                + "                    \"end\": 14,\n"
                                + "                    \"tip\": \"관리자의 승인을 받기까지 최대 48시간이 소요됩니다!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"최종게시_캠퍼스픽\",\n"
                                + "                    \"start\": 14,\n"
                                + "                    \"end\": 43,\n"
                                + "                    \"tip\": \"캠퍼스픽에서 대학생들의 문의를 놓치지 않도록 매일 확인하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시_요즘것들\",\n"
                                + "                    \"start\": 11,\n"
                                + "                    \"end\": 43,\n"
                                + "                    \"tip\": \"대학생과 청년을 위한 다양한 활동 정보를 제공하는 요즘것들에 꼭 홍보하세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시_에브리타임\",\n"
                                + "                    \"start\": 11,\n"
                                + "                    \"end\": 43,\n"
                                + "                    \"tip\": \"대학생 대상 홍보엔 에브리타임이 최적! 3일 주기로 원하는 학과 게시판에 꾸준히 올려보세요!\"\n"
                                + "                },\n"
                                + "                {\n"
                                + "                    \"name\": \"게시_인스타그램\",\n"
                                + "                    \"start\": 11,\n"
                                + "                    \"end\": 43,\n"
                                + "                    \"tip\": \"20대를 타겟으로 인스타그램 유행을 활용해 효율적으로 홍보하세요!\"\n"
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
    SuccessResponse<List<PromotionTimelineRes>> createPromotionTimeline(
            @RequestBody PromotionTimelineReq req
    );
}
