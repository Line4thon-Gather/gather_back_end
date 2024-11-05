package org.example.gather_back_end.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import org.example.gather_back_end.repository.PortfolioRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    @Override
    public void createPortfolio(Long userId, List<CreatePortfolioReq> createPortfolioReqList){

        User user = userRepository.getById(userId);

        for(CreatePortfolioReq portfolio : createPortfolioReqList){
            portfolioRepository.save(Portfolio.createPortfolioInfo(
                    user,
                    portfolio.title(),
                    portfolio.thumbnailImgUrl(),
                    portfolio.fileUrl()
            ));
        }
    }
}
