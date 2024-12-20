package org.example.gather_back_end.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;

// 홍보 요청 내역
@Entity
@Table(name = "PromotionRequest")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_request_id")
    private Long id;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 홍보 제목
    @Column(nullable = false)
    private String title;

    // 예상 모집 기간
    @Column(nullable = false)
    private Integer period;

    // 목표 인원
    @Column(nullable = false)
    private Integer targetNumberOfPeople;

    // 보유한 예산
    @Column(nullable = false)
    private Integer budget;

    // 1순위 홍보 수단
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType firstMeans;

    // 2순위 홍보 수단
    @Enumerated(EnumType.STRING)
    private WorkType secondMeans;

    // 3순위 홍보 수단
    @Enumerated(EnumType.STRING)
    private WorkType thirdMeans;

    @OneToOne(mappedBy = "promotionRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private PromotionResult promotionResult;

    public PromotionRequest(User user, String title, Integer period, Integer targetNumberOfPeople, Integer budget,
                            WorkType firstMeans, WorkType secondMeans, WorkType thirdMeans) {
        this.user = user;
        this.title = title;
        this.period = period;
        this.targetNumberOfPeople = targetNumberOfPeople;
        this.budget = budget;
        this.firstMeans = firstMeans;
        this.secondMeans = secondMeans;
        this.thirdMeans = thirdMeans;
    }
}
