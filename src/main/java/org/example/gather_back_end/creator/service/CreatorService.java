package org.example.gather_back_end.creator.service;

import org.example.gather_back_end.creator.dto.GetCreatorRes;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.util.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface CreatorService {

    void createCreator(
            Long userId,
            String nickname,
            String introductionTitle,
            String introductionContent,
            String contactKakaoId,
            String contactEmail
    );

    GetCreatorRes getCreator(String nickname);

    GetCreatorRes getCreatorInfo(Authentication authentication);
    PageResponse<CreatorInfo> filteringCreator(String providerId, Pageable pageable, Integer price, String category, String align);
}
