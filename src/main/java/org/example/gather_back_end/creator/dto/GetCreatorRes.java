package org.example.gather_back_end.creator.dto;

import org.example.gather_back_end.portfolio.dto.GetPortfolioRes;
import org.example.gather_back_end.work.dto.GetWorkRes;

import java.util.List;

public record GetCreatorRes(
        String nickname,
        String profileImgUrl,
        String introductionTitle,
        String introductionContent,
        List<GetPortfolioRes> getPortfolioResList,
        List<GetWorkRes> getWorkResList,
        String contactKakaoId,
        String contactEmail
) {
}
