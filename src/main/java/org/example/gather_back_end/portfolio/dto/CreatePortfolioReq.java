package org.example.gather_back_end.portfolio.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record CreatePortfolioReq(
        String title
) {
}
