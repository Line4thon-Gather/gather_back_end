package org.example.gather_back_end.work.service;

import org.example.gather_back_end.work.dto.CreateWorkReq;

import java.util.List;

public interface WorkService {

    void createWork(Long userId, List<CreateWorkReq> createWorkReqList);

    void deleteWork(Long userId);
}
