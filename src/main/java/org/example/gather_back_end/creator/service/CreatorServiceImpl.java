package org.example.gather_back_end.creator.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.creator.dto.filtering.FilteringCreatorRes;
import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.dto.GetPortfolioRes;
import org.example.gather_back_end.repository.PortfolioRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.WorkRepository;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.work.dto.GetWorkRes;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatorServiceImpl implements CreatorService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final WorkRepository workRepository;

    // 크리에이터 등록
    @Override
    public void createCreator(
            Long userId,
            String nickname,
            String introductionTitle,
            String introductionContent,
            String contactKakaoId,
            String contactEmail
    ){

        User user = userRepository.getById(userId);
        user.createCreatorInfo(user, nickname, introductionTitle, introductionContent, contactKakaoId, contactEmail);
        userRepository.save(user);
    };

    // 크리에이터 상세 페이지 조회
    @Override
    public GetCreatorRes getCreator(String nickname){

        User user = userRepository.getByNickname(nickname);

        List<GetPortfolioRes> getPortfolioResList = portfolioRepository.getAllByUser(user);
        List<GetWorkRes> getWorkResList = workRepository.findAllByUser(user);

        // GetCreatorRes에 하나하나 다 담아야함
        GetCreatorRes res = new GetCreatorRes(
                nickname,
                user.getProfileImgUrl(),
                user.getIntroductionTitle(),
                user.getIntroductionContent(),
                getPortfolioResList,
                getWorkResList,
                user.getContactKakaoId(),
                user.getContactEmail()
                );

        return res;

    }

    // 크리에이터 등록 초기 화면
    @Override
    public GetCreatorRes getCreatorInfo(Authentication authentication){

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        User user = userRepository.getByUsername(customOAuth2User.getUsername());

        List<GetPortfolioRes> getPortfolioResList = portfolioRepository.getAllByUser(user);
        List<GetWorkRes> getWorkResList = workRepository.findAllByUser(user);

        // GetCreatorRes에 하나하나 다 담아야함
        GetCreatorRes res = new GetCreatorRes(
                user.getNickname(),
                user.getProfileImgUrl(),
                user.getIntroductionTitle(),
                user.getIntroductionContent(),
                getPortfolioResList,
                getWorkResList,
                user.getContactKakaoId(),
                user.getContactEmail()
        );

        return res;

    }

    @Override
    public FilteringCreatorRes filteringCreator(String providerId, Integer price, String category, String align) {
        return null;
    }

}
