package org.example.gather_back_end.bucket.service;

import org.example.gather_back_end.user.dto.UploadProfileImgRes;
import org.springframework.web.multipart.MultipartFile;

public interface BucketService {

    // 프로필 이미지 업로드
    UploadProfileImgRes uploadProfileImg(MultipartFile file, Long userId) throws Exception;

    // 파일 경로 받아오기
    Long getPublicImgUrl(Long userId) throws Exception;

    // 파일 다운로드
    MultipartFile downloadFile(Long userId, String type) throws Exception;
}
