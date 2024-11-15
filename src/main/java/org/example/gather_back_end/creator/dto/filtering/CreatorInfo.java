package org.example.gather_back_end.creator.dto.filtering;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.Work;

public record CreatorInfo(
        @Schema(description = "크리에이터명", example = "hello")
        String nickname,

        @Schema(description = "가능한 작업 카테고리", example = "[\"SNS\", \"인쇄물\", \"비디오\"]")
        List<String> availableWork,

        @Schema(description = "크리에이터 소개글 제목", example = "안녕하세요")
        String introductionTitle,

        @Schema(description = "작업 시작 가격", example = "5000")
        String startPrice,

        @Schema(description = "포트폴리오 썸네일 주소", example = "dfdfd")
        String thumbnailImgUrl
) {

    public static CreatorInfo from(User user, List<String> availableWork, List<Portfolio> portfolioList, String align) {

        // align 파라미터에 따라 startPrice 설정
        String startPrice;
        if ("highPrice".equalsIgnoreCase(align)) {
            startPrice = user.getWorkList().stream()
                    .map(Work::getStartPrice)
                    .max(Integer::compareTo)
                    .map(String::valueOf)
                    .orElse("N/A");
        } else { // 기본값은 minPrice
            startPrice = user.getWorkList().stream()
                    .map(Work::getStartPrice)
                    .min(Integer::compareTo)
                    .map(String::valueOf)
                    .orElse("N/A");
        }

        // availableWork는 이미 변환된 상태로 전달됨
        List<String> translatedAvailableWork = availableWork;

        // 포트폴리오 썸네일 설정
        String thumbnailImgUrl = portfolioList.isEmpty() ? null : portfolioList.get(0).getThumbnailImgUrl();

        return new CreatorInfo(
                user.getNickname(),
                translatedAvailableWork,
                user.getIntroductionTitle(),
                startPrice,
                thumbnailImgUrl
        );
    }

}
