package org.example.gather_back_end.util.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // TODO : PROTECTED로 변경
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //provider Id
    private String username;

    //실명
    private String name;

    //이메일
    private String email;

    //권한
    private String role;

    //크리에이터명
    private String nickname;

    @Builder
    private User(Long id, String username, String name, String email, String role, String nickname) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
    }

    public static User createAllUserInfo(String username, String name, String email, String role, String nickname) {
        return User.builder()
                .username(username)
                .name(name)
                .email(email)
                .role(role)
                .nickname(nickname)
                .build();
    }

    // 업데이트 메서드
    public void updateUserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
