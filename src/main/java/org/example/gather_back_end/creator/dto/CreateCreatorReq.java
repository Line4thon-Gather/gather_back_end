package org.example.gather_back_end.creator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import org.example.gather_back_end.work.dto.CreateWorkReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Builder
public record CreateCreatorReq(
        @Schema(description = "사용자가 입력한 크리에이터명", example = "Hong티쳐")
        String nickname,

        @Schema(description = "사용자가 입력한 소개글 제목", example = "전부 다 합니다.")
        String introductionTitle,

        @Schema(description = "사용자가 입력한 소개글", example = "이것저것 한지 오래 됐어요")
        String introductionContent,

        @Schema(description = "카카오톡 아이디", example = "Legend")
        String contactKaKaoId,

        @Schema(description = "사용자 이메일", example = "Hong@skuniv.ac.kr")
        String contactEmail,

        @Schema(description = "사용자 본인 포트폴리오",
                example = """
                [
                    {
                        "title": "이것도 합니다."
                    },
                    {
                        "title": "저것도 합니다"
                    }
                ]""")
        List<CreatePortfolioReq> createPortfolioReqList,

        @Schema(description = "사용자 작업 가능 항목",
                example = """
                        [
                            {
                                "title": "다좋아",
                                "period": 13,
                                "startPrice": 25000,
                                "category": "PRINTS"
                            },
                            {
                                "title": "그냥 좋아",
                                "period": 52,
                                "startPrice": 245000,
                                "category": "PRINTS"
                            }
                        ]
                        """
        )
        List<CreateWorkReq> createWorkReqList
) {

    public static CreateCreatorReq from(User user) {
        return CreateCreatorReq.builder()
                .nickname(user.getNickname())
                .introductionTitle(user.getIntroductionTitle())
                .introductionContent(user.getIntroductionContent())
                .contactKaKaoId(user.getContactKakaoId())
                .contactEmail(user.getContactEmail())
                .build();
    }
}

