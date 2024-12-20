package org.example.gather_back_end.util.jwt.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.bucket.service.BucketService;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.repository.UserRepository;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.jwt.dto.GoogleResponse;
import org.example.gather_back_end.util.jwt.dto.OAuth2Response;
import org.example.gather_back_end.util.jwt.dto.UserDto;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService implements CustomOAuth2UserService {

    private final UserRepository userRepository;
    private final LocalDateTimeNumericEncryption localDateTimeNumericEncryption;
    private final BucketService bucketService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("[CustomOAuth2UserService 클래스][loadUser 메소드] : " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if (registrationId.equals("google")) { oAuth2Response = new GoogleResponse(oAuth2User.getAttributes()); }
        else { return null; }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        StringBuilder numericEncryptedDateTime;

        while(true) {
            SecretKey secretKey;
            try {
                secretKey = KeyGenerator.getInstance(localDateTimeNumericEncryption.getALGORITHM()).generateKey();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            LocalDateTime code = LocalDateTime.now();
            try {
                numericEncryptedDateTime = new StringBuilder(localDateTimeNumericEncryption.encryptToSixDigits(code, secretKey));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (userRepository.findByNickname("USER" + numericEncryptedDateTime).isEmpty()) {
                break;
            }
        }

        String nickname = "USER" + numericEncryptedDateTime;

        User existData = userRepository.findByUsernameCustom(username);

        // Default로 기본 이미지 넣기 (default_profile.png <- OCI)
        if (existData == null) {

            userRepository.save(User.createUserInfo(
                    bucketService.defaultProfileImgUrl(),
                    username,
                    oAuth2Response.getName(),
                    oAuth2Response.getEmail(),
                    "ROLE_USER",
                    nickname)
            );

            UserDto userDto = UserDto.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .build();

            return new CustomOAuth2User(userDto);

        } else {

            // 유저 정보 업데이트
            existData.updateUserInfo(oAuth2Response.getName(), oAuth2Response.getEmail());

            // 업데이트 된 유저 정보
            userRepository.save(existData);

            // 수정된 이름과 이메일을 다시 토큰에 넣기
            UserDto userDto = UserDto.builder()
                    .username(existData.getUsername())
                    .name(existData.getName())
                    .role(existData.getRole())
                    .build();

            return new CustomOAuth2User(userDto);
        }
    }
}
