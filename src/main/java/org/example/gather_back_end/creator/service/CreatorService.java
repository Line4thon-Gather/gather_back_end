package org.example.gather_back_end.creator.service;

import org.example.gather_back_end.domain.Portfolio;

import java.util.List;

public interface CreatorService {

    void createCreator(String username, String introductionTitle, String introductionContent, String contactKakaoId, String contactEmail, List<Portfolio> portfolioList);
}
