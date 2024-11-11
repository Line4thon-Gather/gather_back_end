package org.example.gather_back_end.creator.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import org.example.gather_back_end.work.dto.CreateWorkReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Builder
public record CreateCreatorReq(
        String nickname,
        String introductionTitle,
        String introductionContent,
        String contactKaKaoId,
        String contactEmail,
        List<CreatePortfolioReq> createPortfolioReqList,
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

