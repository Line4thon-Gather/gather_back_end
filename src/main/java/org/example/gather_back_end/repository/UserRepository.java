package org.example.gather_back_end.repository;

import org.example.gather_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    User findByUsername(String username);
}
