package org.example.gather_back_end.util.jwt.repository;

import org.example.gather_back_end.util.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
