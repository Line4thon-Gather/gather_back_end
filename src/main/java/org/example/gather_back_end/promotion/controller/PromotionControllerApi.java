package org.example.gather_back_end.promotion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.gather_back_end.promotion.dto.promotion.PromotionRes;
import org.example.gather_back_end.promotion.dto.timeline.PromotionTimelineReq;
import org.example.gather_back_end.util.response.SuccessResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "홍보 타임라인 관련", description = "타임라인 + 비용 관리 + 크리에이터 추천")
public interface PromotionControllerApi {

    @Operation(summary = "타임라인 + 비용관리 + 크리에이터 추천",
            description = "최종 완성"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "호출 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "2024-11-13T18:24:10.841273",
                                                "isSuccess": true,
                                                "code": "200",
                                                "message": "호출에 성공하였습니다.",
                                                "data": {
                                                    "timelineList": [
                                                        {
                                                            "period": 20,
                                                            "category": "PRINTS",
                                                            "tasks": [
                                                                {
                                                                    "name": "기획",
                                                                    "start": 0,
                                                                    "end": 2,
                                                                    "tip": "단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!"
                                                                },
                                                                {
                                                                    "name": "디자인",
                                                                    "start": 2,
                                                                    "end": 8,
                                                                    "tip": "단체의 색깔과 캐릭터를 정하고, 원하는 내용을 간결하게 정리해 디자이너와 원활히 소통하세요!"
                                                                },
                                                                {
                                                                    "name": "출력",
                                                                    "start": 8,
                                                                    "end": 9,
                                                                    "tip": "시간적 여유가 있다면 Bizhows로, 급하다면 학교나 인쇄소에서 사이즈를 미리 확인 후 인쇄하세요!"
                                                                },
                                                                {
                                                                    "name": "게시요청_캠퍼스픽",
                                                                    "start": 9,
                                                                    "end": 10,
                                                                    "tip": "접근성 높은 캠퍼스픽에 포스터를 준비해 효과적으로 홍보하세요!"
                                                                },
                                                                {
                                                                    "name": "승인기간_캠퍼스픽",
                                                                    "start": 10,
                                                                    "end": 12,
                                                                    "tip": "관리자의 승인을 받기까지 최대 48시간이 소요됩니다!"
                                                                },
                                                                {
                                                                    "name": "최종게시_캠퍼스픽",
                                                                    "start": 12,
                                                                    "end": 20,
                                                                    "tip": "캠퍼스픽에서 대학생들의 문의를 놓치지 않도록 매일 확인하세요!"
                                                                },
                                                                {
                                                                    "name": "게시_요즘것들",
                                                                    "start": 9,
                                                                    "end": 20,
                                                                    "tip": "대학생과 청년을 위한 다양한 활동 정보를 제공하는 요즘것들에 꼭 홍보하세요!"
                                                                },
                                                                {
                                                                    "name": "게시_에브리타임",
                                                                    "start": 9,
                                                                    "end": 20,
                                                                    "tip": "대학생 대상 홍보엔 에브리타임이 최적! 3일 주기로 원하는 학과 게시판에 꾸준히 올려보세요!"
                                                                },
                                                                {
                                                                    "name": "게시_인스타그램",
                                                                    "start": 9,
                                                                    "end": 20,
                                                                    "tip": "20대를 타겟으로 인스타그램 유행을 활용해 효율적으로 홍보하세요!"
                                                                }
                                                            ]
                                                        },
                                                        {
                                                            "period": 20,
                                                            "category": "SNS_POST",
                                                            "tasks": [
                                                                {
                                                                    "name": "내용 정리 및 컨택",
                                                                    "start": 0,
                                                                    "end": 2,
                                                                    "tip": "단체의 키워드와 아이덴티티를 명확히 정리해, 디자이너와 최고의 결과물을 만들어보세요!"
                                                                },
                                                                {
                                                                    "name": "제작",
                                                                    "start": 2,
                                                                    "end": 6,
                                                                    "tip": "담당자를 지정해 크리에이터와 빠르고 예의 있게 소통하세요!"
                                                                },
                                                                {
                                                                    "name": "게시_인스타그램",
                                                                    "start": 6,
                                                                    "end": 20,
                                                                    "tip": "일 5000원 투자로 짧은 기간에 최대 효과로 더 많은 사람들에게 정보를 알리세요!"
                                                                }
                                                            ]
                                                        }
                                                    ],
                                                    "costList": [
                                                        {
                                                            "means": "인쇄물",
                                                            "cost": "44,000",
                                                            "rate": 70
                                                        },
                                                        {
                                                            "means": "SNS 게시물",
                                                            "cost": "18,800",
                                                            "rate": 30
                                                        }
                                                    ],
                                                    "creatorList": [
                                                        {
                                                            "nickname": "USER3322",
                                                            "availableWork": [
                                                                "인쇄물",
                                                                "SNS 게시물"
                                                            ],
                                                            "introductionTitle": "dkssudd",
                                                            "startPrice": "2,000",
                                                            "thumbnailImgUrl": ""
                                                        },
                                                        {
                                                            "nickname": "USER3333",
                                                            "availableWork": [
                                                                "SNS 게시물"
                                                            ],
                                                            "introductionTitle": "dfdfdfdfdfdfdfdf",
                                                            "startPrice": "1,000",
                                                            "thumbnailImgUrl": null
                                                        }
                                                    ]
                                                }
                                            }
                        """
                            ),
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            )
    })
    @PostMapping
    SuccessResponse<PromotionRes> createAllPromotionInfo(
            Authentication authentication,
            @RequestBody PromotionTimelineReq req
    );
}
