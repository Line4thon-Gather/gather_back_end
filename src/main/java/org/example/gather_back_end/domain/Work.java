package org.example.gather_back_end.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.gather_back_end.util.entity.BaseEntity;

@Entity
@Table(name = "Works")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long id;

    // TODO: 회원 FK 연관관계 (일대다)

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
}
