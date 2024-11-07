package org.example.gather_back_end.bucket.service;

import org.springframework.web.multipart.MultipartFile;

public interface BucketService {

    // 프로필 이미지 업로드
    void uploadProfileImg(MultipartFile file, Long userId) throws Exception;

    // 파일 경로 받아오기
    Long getPublicImgUrl(Long userId) throws Exception;

    // 파일 다운로드
    MultipartFile downloadFile(Long userId, String type) throws Exception;

    String defaultProfileImgUrl();

    String createThumbnailImg(MultipartFile file) throws Exception;

    String createPortfolioPdf(MultipartFile file) throws Exception;

}
