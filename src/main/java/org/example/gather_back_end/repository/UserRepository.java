package org.example.gather_back_end.repository;

import java.util.List;
import java.util.Optional;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.WorkType;
import org.example.gather_back_end.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsernameCustom(@Param("username") String username);

    Optional<User> findByUsername(String username);

    default User getById(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

    default User getByUsername(String username) {
        return findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findById(Long id);

    Optional<User> findByNickname(String nickname);

    default User getByNickname(String nickname) {
        return findByNickname(nickname).orElseThrow(UserNotFoundException::new);
    }

    // 크리에이터 찾기
    @Query("SELECT u FROM User u " +
           "JOIN u.workList w " +
           "JOIN u.portfolioList p " +
           "WHERE u.introductionTitle IS NOT NULL " + // 소개글 제목 존재
           "AND SIZE(u.workList) > 0 " + // 작업 가능 항목 등록
           "AND SIZE(u.portfolioList) > 0 ")
    // 포트폴리오 등록
    // TODO: 포트폴리오 더미데이터 모두 넣은 후 주석 해제
//           "AND SIZE(u.portfolioList) > 0 " + // 포트폴리오 등록
//           "AND p.thumbnailImgUrl IS NOT NULL " + // 포트폴리오 썸네일 존재
//           "AND p.fileUrl IS NOT NULL") // 포트폴리오 파일 존재
    List<User> findAllCreators();

    @Query("SELECT DISTINCT u FROM User u " +
           "JOIN u.workList w " +
           "WHERE u.introductionTitle IS NOT NULL " +
           "AND (:price IS NULL OR " +
           "     (:price = 10000 AND w.startPrice < 10000) OR " +
           "     (:price = 50000 AND w.startPrice < 50000) OR " +
           "     (:price = 100000 AND w.startPrice < 100000) OR " +
           "     (:price = 200000 AND w.startPrice < 200000) OR " +
           "     (:price = 200001 AND w.startPrice >= 200000)) " +
           "AND (:category IS NULL OR w.category = :category)")
    Page<User> customFiltering(@Param("price") Integer price, @Param("category") WorkType category, Pageable pageable);

}
