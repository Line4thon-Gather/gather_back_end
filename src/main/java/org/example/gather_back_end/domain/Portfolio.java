package org.example.gather_back_end.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Portfolio")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 포트폴리오 제목
    @Column(nullable = false, length = 10)
    private String title;

    // 포트폴리오 썸네일 주소
    @Column(columnDefinition = "TEXT")
    private String thumbnailImgUrl;

    // 포트폴리오 파일 주소
    @Column(nullable = false, columnDefinition = "TEXT")
    private  String fileUrl;

    //포트폴리오 생성
    public static Portfolio createPortfolioInfo(User user, String title, String thumbnailImgUrl, String fileUrl) {
        return Portfolio.builder()
                .user(user)
                .title(title)
                .thumbnailImgUrl(thumbnailImgUrl)
                .fileUrl(fileUrl)
                .build();
    }

}
