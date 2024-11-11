package org.example.gather_back_end.portfolio.service;

import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface PortfolioService {

    void createPortfolio(
            Long userId,
            List<CreatePortfolioReq> createPortfolioReqList,
            List<MultipartFile> thumbnailImgUrlList,
            List<MultipartFile> portfolioPdfList
    );

    void deletePortfolio(Long userId);
}
