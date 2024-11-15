package org.example.gather_back_end.view.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.creator.dto.filtering.CreatorInfo;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
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

        // 현재 로그인한 사용자의 닉네임 가져오기
        String currentLoginUserNickname = userRepository.getByUsername(providerId).getNickname();

        // ViewRecord에서 현재 로그인한 사용자가 본 기록 가져오기
        List<ViewRecord> byCurrentLoginUserNickname = viewRecordRepository.findByCurrentLoginUserNickname(currentLoginUserNickname);

        // CreatorInfo 리스트 생성
        List<CreatorInfo> creatorInfoList = new ArrayList<>();

        // ViewRecord 리스트를 순회하며 CreatorInfo 변환
        for (ViewRecord record : byCurrentLoginUserNickname) {
            String seenUserNickname = record.getCurrentSeenUserNickname();
            User seenUser = userRepository.getByNickname(seenUserNickname);

            if (seenUser != null) {
                // Work 리스트의 카테고리 가져오기
                List<String> availableWork = seenUser.getWorkList()
                        .stream()
                        .map(work -> work.getCategory().name())
                        .toList();

                // Portfolio 리스트 가져오기
                List<Portfolio> portfolioList = seenUser.getPortfolioList();

                // CreatorInfo 변환 및 추가
                creatorInfoList.add(CreatorInfo.from(seenUser, availableWork, portfolioList, "lowPrice", null));
            }
        }

        return creatorInfoList;
    }

}
