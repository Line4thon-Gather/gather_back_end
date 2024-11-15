package org.example.gather_back_end.view.service;

import io.swagger.v3.oas.annotations.servers.Server;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.ViewRecordRepository;

@Slf4j
@Server
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final UserRepository userRepository;
    private final ViewRecordRepository viewRecordRepository;

    @Override
    public void execute(String providerId, String nickname) {

        // 사용자 찾기
        User currentLoginUser = userRepository.getByUsername(providerId); // 현재 로그인한 사용자
        User currentSeenUser = userRepository.getByNickname(nickname); // 현재 로그인한 사용자가 본 포폴 사용자

    }

    @Override
    public List<CreatorInfo> getViewCreatorList(String providerId) {
        return null;
    }
}
