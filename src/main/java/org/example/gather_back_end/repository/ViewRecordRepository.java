package org.example.gather_back_end.repository;

import java.util.List;
import java.util.Optional;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.domain.User;
import org.example.gather_back_end.domain.ViewRecord;
import org.example.gather_back_end.portfolio.dto.GetPortfolioRes;
import org.example.gather_back_end.portfolio.exception.PortfolioNotFoundException;
import org.example.gather_back_end.view.ViewRecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRecordRepository extends JpaRepository<ViewRecord, Long> {

    default ViewRecord getById(Long id) {
        return findById(id).orElseThrow(ViewRecordNotFoundException::new);
    }

    Optional<ViewRecord> findById(Long id);
}
