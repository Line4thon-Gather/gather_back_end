package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.ViewRecord;
import org.example.gather_back_end.view.exception.ViewRecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRecordRepository extends JpaRepository<ViewRecord, Long> {

    default ViewRecord getById(Long id) {
        return findById(id).orElseThrow(ViewRecordNotFoundException::new);
    }

    Optional<ViewRecord> findById(Long id);
    Optional<ViewRecord> findByCurrentLoginUserNicknameAndAndCurrentSeenUserNickname(
            String currentLoginUserNickname,
            String currentSeenUserNickname
    );
}
