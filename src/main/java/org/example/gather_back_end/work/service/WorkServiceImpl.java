package org.example.gather_back_end.work.service;

import lombok.RequiredArgsConstructor;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.Work;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.repository.WorkRepository;
import org.example.gather_back_end.work.dto.CreateWorkReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final UserRepository userRepository;
    private final WorkRepository workRepository;

    @Override
    public void createWork(Long userId, List<CreateWorkReq> createWorkReqList){

        // user 테이블과 맵핑
        User user = userRepository.getById(userId);

        // JSON에서 추출한 Work 리스트를 for문을 통해 데이터 생성
        for(CreateWorkReq createWorkReq : createWorkReqList){
            workRepository.save(
                    Work.createWorkInfo(
                            user,
                            createWorkReq.title(),
                            createWorkReq.period(),
                            createWorkReq.startPrice(),
                            createWorkReq.category()
                    )
            );
        }
    };
}
