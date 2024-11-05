package org.example.gather_back_end.domain;

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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Table(name = "Works")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Work extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long id;

    // 회원 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 작업명
    @Column(nullable = false)
    private String title;

    // 작업 기간
    @Column(nullable = false)
    private Integer period;

    // 시작 가격
    @Column(nullable = false)
    private Integer startPrice;

    // 작업 카테고리
    @Enumerated(EnumType.STRING)
    private WorkType category;

    public static Work createWorkInfo(User user, String title, Integer period, Integer startPrice, WorkType category) {
        return Work.builder()
                .user(user)
                .title(title)
                .period(period)
                .startPrice(startPrice)
                .category(category)
                .build();
    }
}
