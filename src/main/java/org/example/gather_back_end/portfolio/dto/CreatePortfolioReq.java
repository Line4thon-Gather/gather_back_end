package org.example.gather_back_end.portfolio.dto;

import lombok.Builder;

@Builder
public record CreatePortfolioReq(
        String title,
        String thumbnailImgUrl,
        String fileUrl
) {
}
