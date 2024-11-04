package org.example.gather_back_end.creator.dto;

import lombok.Builder;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.Work;

import java.util.*;

@Builder
public record CreateCreatorReq(
        String introductionTitle,
        String introductionContent,
        String contactKaKaoId,
        String contactEmail,
        List<Portfolio> portfolioList,
        List<Work> workList
) {

    public static CreateCreatorReq from(User user) {
        return CreateCreatorReq.builder()
                .introductionTitle(user.getIntroductionTitle())
                .introductionContent(user.getIntroductionContent())
                .contactKaKaoId(user.getContactKakaoId())
                .contactEmail(user.getContactEmail())
                .portfolioList(user.getPortfolioList())
                .workList(user.getWorkList())
                .build();
    }
}

