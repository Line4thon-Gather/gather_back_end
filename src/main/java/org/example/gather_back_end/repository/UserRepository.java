package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.portfolio.exception.PortfolioNotFoundException;
import org.example.gather_back_end.user.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);
    User findByUsername(String username);
    Optional<User> findByUsername2(String username);

    default User getById(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }
    default User getByUsername(String username) {
        return findByUsername2(username).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findById(Long id);
}
