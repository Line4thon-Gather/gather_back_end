package org.example.gather_back_end.bucket.service;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.ConfigFileReader.ConfigFile;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadRequest;
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadResponse;
import java.io.File;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class BucketServiceImpl implements BucketService {

    @Value("${bucket.name}")
    private String BUCKET_NAME;

    @Value("${bucket.name_space}")
    private static String BUCKET_NAME_SPACE;

    @Value("${bucket.profile_img_dir}")
    private String BUCKET_PROFILE_IMG_DIR;

    public static final String DEFAULT_URI_PREFIX = "https://" + BUCKET_NAME_SPACE + ".objectstorage."
                                                    + Region.AP_CHUNCHEON_1.getRegionId() + ".oci.customer-oci.com";

    public ObjectStorage getClient() throws Exception {
        ConfigFile config = ConfigFileReader.parse("~/.oci/config", "DEFAULT");

        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider(config);

        return ObjectStorageClient.builder()
                .region(Region.AP_CHUNCHEON_1)
                .build(provider);
    }

    public UploadManager getManager(ObjectStorage client) throws Exception {
        UploadConfiguration configuration = UploadConfiguration.builder()
                .allowMultipartUploads(true)
                .allowParallelUploads(true)
                .build();
        return new UploadManager(client, configuration);
    }

    @Override
    public Long uploadProfileImg(MultipartFile file, Long userId) throws Exception {
        return null;
    }

    @Override
    public Long getPublicImgUrl(Long userId) throws Exception {
        return null;
    }

    @Override
    public MultipartFile downloadFile(Long userId, String type) throws Exception {
        return null;
    }

    public Long upload(File uploadFile, String dirName, User user) throws Exception {
        ObjectStorage client = getClient();
        UploadManager uploadManager = getManager(client);

        String fileName = dirName + UUID.randomUUID() + uploadFile.getName();   // 버킷에 저장된 파일 이름
        String contentType = "test/" + fileName.substring(fileName.length() - 3); // PNG, JPG 만 가능함

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucketName(BUCKET_NAME)
                        .namespaceName(BUCKET_NAME_SPACE)
                        .objectName(fileName)
                        .contentType(contentType)
                        .build();
        UploadRequest uploadDetails =
                UploadRequest.builder(uploadFile).allowOverwrite(true).build(request);

        UploadResponse response = uploadManager.upload(uploadDetails);
        log.info("Upload Success. File : {}", fileName);

        client.close();
        removeNewFile(uploadFile);
        return saveImageToUser(user, fileName);
    }

    public void removeNewFile(File targetFile) {
        log.info("@@@@@@@@ 지울 대상 파일 이름"+targetFile.getName());
        log.info("@@@@@@@@ 지울 대상 파일 경로"+targetFile.getPath());
        if (targetFile.exists()) {
            if (targetFile.delete()) {
                log.info("@@@@@@@@ File delete success");
                return;
            }
            log.info("@@@@@@@@ File delete fail.");
        }
        log.info("@@@@@@@@ File not exist.");
    }

}
