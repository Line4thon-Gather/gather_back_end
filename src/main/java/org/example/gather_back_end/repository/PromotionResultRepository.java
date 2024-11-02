package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.PromotionResult;
import org.example.gather_back_end.promotionresult.exception.PromotionResultNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionResultRepository extends JpaRepository<PromotionResult, Long> {

    default PromotionResult getById(Long id) {
        return findById(id).orElseThrow(PromotionResultNotFoundException::new);
    }

    Optional<PromotionResult> findById(Long id);
}
