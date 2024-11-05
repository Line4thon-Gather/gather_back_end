package org.example.gather_back_end.portfolio.service;

import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import java.util.List;

public interface PortfolioService {

    void createPortfolio(Long userId, List<CreatePortfolioReq> createPortfolioReqs);

    void deletePortfolio(Long userId);
}
