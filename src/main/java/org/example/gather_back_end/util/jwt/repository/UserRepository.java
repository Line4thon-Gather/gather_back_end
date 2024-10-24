package org.example.gather_back_end.util.jwt.repository;

import org.example.gather_back_end.util.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
