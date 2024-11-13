package org.example.gather_back_end.creator.service;

import org.example.gather_back_end.creator.dto.GetCreatorRes;
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
}
