package org.example.gather_back_end.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.bucket.service.BucketService;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.dto.CreatePortfolioReq;
import org.example.gather_back_end.repository.PortfolioRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final BucketService bucketService;

    @Override
    public void createPortfolio(Long userId, List<CreatePortfolioReq> createPortfolioReqList){

        User user = userRepository.getById(userId);

        for (CreatePortfolioReq portfolio : createPortfolioReqList){
            try {
                portfolioRepository.save(Portfolio.createPortfolioInfo(
                        user,
                        portfolio.title(),
                        bucketService.createThumbnailImg(portfolio.thumbnailImgUrl()),
                        bucketService.createPortfolioPdf(portfolio.fileUrl())
                ));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    @Transactional
    public void deletePortfolio(Long userId){
        User user = userRepository.getById(userId);

        if (portfolioRepository.findAllByUser(user).isPresent()) {
            portfolioRepository.deleteAllByUser(user);
        }

    }

}
