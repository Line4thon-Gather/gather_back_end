package org.example.gather_back_end.creator.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.WorkType;
import org.example.gather_back_end.portfolio.dto.GetPortfolioRes;
import org.example.gather_back_end.repository.PortfolioRepository;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.WorkRepository;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.response.PageResponse;
import org.example.gather_back_end.work.dto.GetWorkRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
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
    public PageResponse<CreatorInfo> filteringCreator(Pageable pageable, Integer price, String category, String align) {

        Sort sort = switch (align) {
            case "lowPrice" -> Sort.by(Sort.Direction.ASC, "workList.startPrice");
            case "highPrice" -> Sort.by(Sort.Direction.DESC, "workList.startPrice");
            default -> Sort.by(Sort.Direction.DESC, "createAt");
        };

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // category를 WorkType으로 변환, 값이 없는 경우 null로 설정
        WorkType workCategory = (category != null) ? WorkType.valueOf(category) : null;

        Page<User> creators = userRepository.customFiltering(price, workCategory, sortedPageable);

        Set<Long> seenIds = new LinkedHashSet<>();
        List<CreatorInfo> creatorInfoList = creators.getContent().stream()
                .filter(user -> seenIds.add(user.getId()))
                .map(user -> CreatorInfo.from(
                        user,
                        user.getWorkList().stream()
                                .map(work -> work.getCategory().name())
                                .toList(),
                        user.getPortfolioList()
                ))
                .collect(Collectors.toList());

        PageImpl<CreatorInfo> res = new PageImpl<>(creatorInfoList, sortedPageable, creatorInfoList.size());

        return PageResponse.of(res);
    }


}
