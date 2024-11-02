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
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.user.dto.UploadProfileImgRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    @Value("${bucket.name}")
    private String BUCKET_NAME;

    @Value("${bucket.name_space}")
    private String BUCKET_NAME_SPACE;

    @Value("${bucket.profile_img_dir}")
    private String BUCKET_PROFILE_IMG_DIR;

    private final UserRepository userRepository;

    public static final String DEFAULT_URI_PREFIX = "https://objectstorage.ap-chuncheon-1.oraclecloud.com/n/";

    private ObjectStorage getClient() throws Exception {
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

    @Override
    public UploadProfileImgRes uploadProfileImg(MultipartFile file, Long userId) throws Exception {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userOpt.get();

        File uploadFile = convertMultiPartToFile(file);
        ObjectStorage client = getClient();
        UploadManager uploadManager = getManager(client);

        String fileName = BUCKET_PROFILE_IMG_DIR + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
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

        log.info("Image uploaded and URL saved to user profile: {}", profileImgUrl);
        client.close();
        return UploadProfileImgRes.from(profileImgUrl);
    }

    @Override
    public Long getPublicImgUrl(Long userId) throws Exception {
        return null;
    }

    @Override
    public MultipartFile downloadFile(Long userId, String type) throws Exception {
        return null;
    }

    private File convertMultiPartToFile(MultipartFile file) throws Exception {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
