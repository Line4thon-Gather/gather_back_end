package org.example.gather_back_end.portfolio.dto;

public record GetPortfolioRes(
        String title,
        String thumbnailImgUrl,
        String fileUrl
) {
}
