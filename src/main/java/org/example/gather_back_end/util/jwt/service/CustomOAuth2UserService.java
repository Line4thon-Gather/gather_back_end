package org.example.gather_back_end.util.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gather_back_end.util.jwt.dto.CustomOAuth2User;
import org.example.gather_back_end.util.jwt.dto.GoogleResponse;
import org.example.gather_back_end.util.jwt.dto.OAuth2Response;
import org.example.gather_back_end.util.jwt.dto.UserDto;
import org.example.gather_back_end.util.jwt.entity.User;
import org.example.gather_back_end.util.jwt.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final LocalDateTimeNumericEncryption localDateTimeNumericEncryption;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;
        if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        StringBuilder numericEncryptedDateTime;
        while(true){
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

            if(userRepository.findByNickname("USER"+numericEncryptedDateTime)==null){};
                break;
        }

        String nickname = "USER"+numericEncryptedDateTime;

        User existData = userRepository.findByNickname(nickname);

        if (existData == null) {

            User user = new User();
            user.setUsername(username);
            user.setEmail(oAuth2Response.getEmail());
            user.setName(oAuth2Response.getName());
            user.setNickname(nickname);
            user.setRole("ROLE_USER");

            userRepository.save(user);

            UserDto userDto = new UserDto();
            userDto.setNickname(nickname);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2User(userDto);
        }
        else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            UserDto userDto = new UserDto();
            userDto.setNickname(existData.getNickname());
            userDto.setName(oAuth2Response.getName());
            userDto.setRole(existData.getRole());

            return new CustomOAuth2User(userDto);
        }
    }
}
