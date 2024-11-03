package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.user.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

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
}
