package org.example.gather_back_end.util.jwt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.domain.Work;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // provider Id
    private String username;

    // 실명
    private String name;

    // 이메일
    private String email;

    // 권한
    private String role;

    // 크리에이터명
    private String nickname;

    // 크리에이터 프로필 사진 주소
    @Column(columnDefinition = "TEXT")
    private String profileImgUrl;

    // 소개글 제목
    @Column(length = 20)
    private String introductionTitle;

    // 소개글 내용
    @Column(length = 50)
    private String introductionContent;

    // 연락 문의 카카오톡 아이디
    @Column(unique = true)
    private String contactKakaoId;

    // 연락 문의 이메일 주소
    @Column(unique = true)
    private String contactEmail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolioList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PromotionRequest> promotionRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> workList = new ArrayList<>();

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
