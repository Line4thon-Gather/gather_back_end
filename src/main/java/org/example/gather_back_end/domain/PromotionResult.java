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
@Table(name = "PromotionResult")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_result_id")
    private Long id;

    // TODO: 홍보 요청 내역 FK 연관관계 (일대일)

    // 타임라인 이미지 주소
    @Column(nullable = false, columnDefinition = "TEXT")
    private String timelineImgUrl;

    // 인쇄물 비용
    @Column(nullable = false)
    private Integer priceOfPrints;

    // 영상 비용
    @Column(nullable = false)
    private Integer priceOfVideo;

    // SNS 게시물 비용
    @Column(nullable = false)
    private Integer priceOfSNSPost;
}