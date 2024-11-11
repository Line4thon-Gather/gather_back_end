package org.example.gather_back_end.bucket.service;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    @Value("${oracle.bucket.name}")
    private String BUCKET_NAME;

    @Value("${oracle.bucket.name_space}")
    private String BUCKET_NAME_SPACE;

    @Value("${oracle.bucket.profile_img_dir}")
    private String BUCKET_PROFILE_IMG_DIR;

    @Value("${oracle.bucket.thumbnail_img_dir}")
    private String BUCKET_THUMBNAIL_IMG_DIR;

    @Value("${oracle.bucket.pdf_dir}")
    private String BUCKET_PDF_DIR;

    private final UserRepository userRepository;

    public static final String DEFAULT_URI_PREFIX = "https://objectstorage.ap-chuncheon-1.oraclecloud.com/n/";

    private ObjectStorage getClient() throws Exception {
        // 서버에서 작동되어야 하는 경로
        ConfigFileReader.ConfigFile config = ConfigFileReader.parse("~/.oci", "DEFAULT");
        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);
        return ObjectStorageClient.builder().region(Region.AP_CHUNCHEON_1).build(provider);
    }

    private UploadManager getManager(ObjectStorage client) {
        UploadConfiguration configuration = UploadConfiguration.builder()
                .allowMultipartUploads(true)
                .allowParallelUploads(true)
                .build();
        return new UploadManager(client, configuration);
    }

    // 파일을 MultipartFile에서 File로 변환하는 메소드
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        // 임시 파일 생성
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }

    // 이미지 업로드 후 임시 파일 삭제
    private void deleteTempFile(File file) {
        if (file != null && file.exists()) {
            try {
                Files.delete(file.toPath());
                log.info("임시 파일 삭제 완료: {}", file.getAbsolutePath());
            } catch (IOException e) {
                log.error("임시 파일 삭제 실패: {}", file.getAbsolutePath(), e);
            }
        }
    }

    @Override
    public void uploadProfileImg(MultipartFile file, Long userId) throws Exception {
        User user = userRepository.getById(userId);

        File uploadFile = convertMultiPartToFile(file);
        ObjectStorage client = getClient();
        UploadManager uploadManager = getManager(client);

        String fileName = BUCKET_PROFILE_IMG_DIR
                + "/" + UUID.randomUUID()
                + "_" + file.getOriginalFilename();
        String contentType = file.getContentType();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucketName(BUCKET_NAME)
                .namespaceName(BUCKET_NAME_SPACE)
                .objectName(fileName)
                .contentType(contentType)
                .build();

        UploadRequest uploadDetails = UploadRequest.builder(uploadFile).build(request);
        uploadManager.upload(uploadDetails);

        String profileImgUrl = DEFAULT_URI_PREFIX + BUCKET_NAME_SPACE + "/b/" + BUCKET_NAME + "/o/" + fileName.replace("/", "%2F");
        User.updateProfileImgUrl(user, profileImgUrl);
        userRepository.save(user);

        log.info("이미지 업로드 완료: {}", profileImgUrl);

        // 임시 파일 삭제
        deleteTempFile(uploadFile);
        client.close();
    }

    @Override
    public Long getPublicImgUrl(Long userId) throws Exception {
        return null;
    }

    @Override
    public MultipartFile downloadFile(Long userId, String type) throws Exception {
        return null;
    }

    @Override
    public String defaultProfileImgUrl() {
        return DEFAULT_URI_PREFIX + BUCKET_NAME_SPACE + "/b/" + BUCKET_NAME + "/o/" + "profileImg/default_profile.png";
    }

    // 썸네일 이미지 생성
    @Override
    public String createThumbnailImg(MultipartFile file) throws Exception {
        File uploadFile = convertMultiPartToFile(file);
        ObjectStorage client = getClient();
        UploadManager uploadManager = getManager(client);

        String fileName = BUCKET_THUMBNAIL_IMG_DIR
                + "/" + UUID.randomUUID()
                + "_" + file.getOriginalFilename();
        String contentType = file.getContentType();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucketName(BUCKET_NAME)
                .namespaceName(BUCKET_NAME_SPACE)
                .objectName(fileName)
                .contentType(contentType)
                .build();

        UploadRequest uploadDetails = UploadRequest.builder(uploadFile).build(request);
        uploadManager.upload(uploadDetails);

        String thumbNailImgUrl = DEFAULT_URI_PREFIX + BUCKET_NAME_SPACE + "/b/" + BUCKET_NAME + "/o/" + fileName.replace("/", "%2F");

        log.info("이미지 업로드 완료: {}", thumbNailImgUrl);

        // 임시 파일 삭제
        deleteTempFile(uploadFile);

        client.close();

        return thumbNailImgUrl;
    }

    // 포트폴리오 PDF 생성
    @Override
    public String createPortfolioPdf(MultipartFile file) throws Exception {
        File uploadFile = convertMultiPartToFile(file);
        ObjectStorage client = getClient();
        UploadManager uploadManager = getManager(client);

        String fileName = BUCKET_PDF_DIR
                + "/" + UUID.randomUUID()
                + "_" + file.getOriginalFilename();
        String contentType = file.getContentType();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucketName(BUCKET_NAME)
                .namespaceName(BUCKET_NAME_SPACE)
                .objectName(fileName)
                .contentType(contentType)
                .build();

        UploadRequest uploadDetails = UploadRequest.builder(uploadFile).build(request);
        uploadManager.upload(uploadDetails);

        String portfolioPdfUrl = DEFAULT_URI_PREFIX + BUCKET_NAME_SPACE + "/b/" + BUCKET_NAME + "/o/" + fileName.replace("/", "%2F");

        log.info("이미지 업로드 완료: {}", portfolioPdfUrl);

        // 임시 파일 삭제
        deleteTempFile(uploadFile);

        client.close();

        return portfolioPdfUrl;
    }
}