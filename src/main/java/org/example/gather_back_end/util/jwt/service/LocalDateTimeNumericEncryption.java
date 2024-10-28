package org.example.gather_back_end.util.jwt.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;

@Getter
@Setter
@Service
public class LocalDateTimeNumericEncryption {

    private final String ALGORITHM = "AES";

    public String encryptToSixDigits(LocalDateTime dateTime, SecretKey key) throws Exception {
        // LocalDateTime을 문자열로 변환
        StringBuilder dateTimeString;
        dateTimeString = new StringBuilder(dateTime.toString());

        // AES로 암호화
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(dateTimeString.toString().getBytes(StandardCharsets.UTF_8));

        // SHA-256 해시 생성
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(encryptedBytes);

        // 해시 값을 숫자로 변환하여 6자리로 축소
        int number = 0;
        for (int i = 0; i < 4; i++) { // 해시의 첫 4바이트를 사용하여 숫자를 생성
            number = (number << 8) | (hash[i] & 0xFF); // 8비트씩 이동하며 결합
        }

        // 8자리 숫자로 변환
        return String.format("%04d", Math.abs(number) % 10000); // 8자리로 포맷
    }

}