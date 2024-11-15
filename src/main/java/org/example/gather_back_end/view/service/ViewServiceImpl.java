package org.example.gather_back_end.view.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.domain.ViewRecord;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.ViewRecordRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final UserRepository userRepository;
    private final ViewRecordRepository viewRecordRepository;

    @Transactional
    @Override
    public void execute(String providerId, String nickname) {

        // 사용자 닉네임 추출
        String currentLoginUserNickname = userRepository.getByUsername(providerId).getNickname(); // 현재 로그인한 사용자
        String currentSeenUserNickname = userRepository.getByNickname(nickname).getNickname(); // 현재 로그인한 사용자가 본 포폴 사용자

        // 기록 찾기
        Optional<ViewRecord> viewRecord = viewRecordRepository.findByCurrentLoginUserNicknameAndCurrentSeenUserNickname(
                currentLoginUserNickname, currentSeenUserNickname
        );

        // 기록이 없다면 단순 저장
        if (viewRecord.isEmpty()) {
            viewRecordRepository.save(ViewRecord.createViewRecord(currentLoginUserNickname, currentSeenUserNickname));
        } else { // 기록이 있다면 본 count 증가
            viewRecord.get().updateViewCount();
        }
    }

    @Override
    public List<CreatorInfo> getViewCreatorList(String providerId) {
        return null;
    }
}
