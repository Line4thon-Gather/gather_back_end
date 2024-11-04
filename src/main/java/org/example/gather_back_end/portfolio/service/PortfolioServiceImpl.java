package org.example.gather_back_end.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
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
    public List<Portfolio> createPortfolio(String username, List<Portfolio> portfolioList){

        User user =userRepository.getByUsername(username);

        for(Portfolio portfolio : portfolioList){
            portfolioRepository.save(Portfolio.createPortfolioInfo(
                    user,
                    portfolio.getTitle(),
                    portfolio.getThumbnailImgUrl(),
                    portfolio.getFileUrl()
            ));
        }

        return portfolioRepository.findAllByUser(user);
    }
}
