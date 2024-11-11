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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final BucketService bucketService;

    @Override
    public void createPortfolio(
            Long userId,
            List<CreatePortfolioReq> createPortfolioReqList,
            List<MultipartFile> thumbnailImgUrlList,
            List<MultipartFile> portfolioPdfList
            ){

        User user = userRepository.getById(userId);

        for (int i=0; i<createPortfolioReqList.size(); i++) {
            try {
                portfolioRepository.save(Portfolio.createPortfolioInfo(
                        user,
                        createPortfolioReqList.get(i).title(),
                        bucketService.createThumbnailImg(thumbnailImgUrlList.get(i)),
                        bucketService.createPortfolioPdf(portfolioPdfList.get(i))
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
