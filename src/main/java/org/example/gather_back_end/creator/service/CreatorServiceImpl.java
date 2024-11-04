package org.example.gather_back_end.creator.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatorServiceImpl implements CreatorService {

    private final UserRepository userRepository;

    @Override
    public void createCreator(String username, String introductionTitle, String introductionContent, String contactKakaoId, String contactEmail, List<Portfolio> portfolioList){

        User user = userRepository.getByUsername(username);

        user.createCreatorInfo(introductionTitle, introductionContent, contactKakaoId, contactEmail, portfolioList);

        userRepository.save(user);
    };

}
