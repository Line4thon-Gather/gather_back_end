package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.UsersViewRecord;
import org.example.gather_back_end.view.exception.UsersViewRecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersViewRecordRepository extends JpaRepository<UsersViewRecord, Long> {

    default UsersViewRecord getById(Long id) {
        return findById(id).orElseThrow(UsersViewRecordNotFoundException::new);
    }

    Optional<UsersViewRecord> findById(Long id);
}
