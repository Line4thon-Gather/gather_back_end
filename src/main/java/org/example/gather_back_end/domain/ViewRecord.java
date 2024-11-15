package org.example.gather_back_end.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "ViewRecord")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 몇 번 봤는지
    @Builder.Default
    private Integer viewCount = 1;

    // 포폴 본 사람 닉네임
    private String currentLoginUserNickname;

    // 포폴 봄을 당한 사람 닉네임
    private String currentSeenUserNickname;

    // 유저 생성
    public static ViewRecord createViewRecord(String currentLoginUserNickname, String currentSeenUserNickname) {
        return ViewRecord.builder()
                .currentLoginUserNickname(currentLoginUserNickname)
                .currentSeenUserNickname(currentSeenUserNickname)
                .build();
    }

    // viewCount 증가
    public void updateViewCount() {
        this.viewCount++;
    }
}
