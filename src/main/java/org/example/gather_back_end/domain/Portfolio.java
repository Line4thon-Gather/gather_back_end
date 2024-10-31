package org.example.gather_back_end.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Table(name = "Portfolio")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    // TODO: 회원 FK 연관관계 (일대다)

    // 포트폴리오 제목
    @Column(nullable = false, length = 10)
    private String title;

    // 포트폴리오 썸네일 주소
    @Column(columnDefinition = "TEXT")
    private String thumbnailImgUrl;

    // 포트폴리오 파일 주소
    @Column(nullable = false, columnDefinition = "TEXT")
    private  String fileUrl;
}
