package org.example.gather_back_end.creator.dto.filtering;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record CreatorInfo(
        @Schema(description = "크리에이터명", example = "hello")
        String nickname,

        @Schema(description = "가능한 작업 카테고리", example = "")
        List<String> availableWork,

        @Schema(description = "크리에이터 소개글 제목", example = "안녕하세요")
        String introductionTitle,

        @Schema(description = "작업 시작 가격", example = "5000")
        String startPrice,

        @Schema(description = "포트폴리오 썸네일 주소", example = "dfdfd")
        String thumbnailImgUrl
) {
}
