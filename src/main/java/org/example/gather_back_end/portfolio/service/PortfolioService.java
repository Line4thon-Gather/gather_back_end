package org.example.gather_back_end.portfolio.service;

import org.example.gather_back_end.domain.Portfolio;

import java.util.List;

public interface PortfolioService {

    List<Portfolio> createPortfolio(String username, List<Portfolio> portfolioList);

}
