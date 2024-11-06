package org.example.gather_back_end.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Table(name = "Users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 대학생/사업자 여부
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Builder.Default
    private boolean isFirstLogin = false;

    @Builder.Default
    private boolean isAuthenticated = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolioList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PromotionRequest> promotionRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Work> workList = new ArrayList<>();

    // 유저 생성
    public static User createUserInfo(String profileImgUrl, String username, String name, String email, String role, String nickname) {
        return User.builder()
                .profileImgUrl(profileImgUrl)
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


    // 대학생 인증 시 유저 정보 업데이트
    public static void updateStudentAuthInfo(User user) {
        user.userType = UserType.STUDENT;
        user.isFirstLogin = true;
        user.isAuthenticated = true;
    }

    // 사업자 인증 시 유저 정보 업데이트
    public static void updateEntrepreneurAuthInfo(User user) {
        user.userType = UserType.BUSINESS;
        user.isFirstLogin = true;
        user.isAuthenticated = true;
    }

    // 유저 크리에이터 등록
    public void createCreatorInfo(
            User user,
            String nickname,
            String introductionTitle,
            String introductionContent,
            String contactKakaoId,
            String contactEmail
    ) {
        user.nickname = nickname;
        user.introductionTitle = introductionTitle;
        user.introductionContent = introductionContent;
        user.contactKakaoId = contactKakaoId;
        user.contactEmail = contactEmail;
    }

    public static void updateProfileImgUrl(User user, String profileImgUrl) {
        user.profileImgUrl = profileImgUrl;
    }

}
