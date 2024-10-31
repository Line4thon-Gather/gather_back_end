package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.PromotionRequest;
import org.example.gather_back_end.promotionrequest.exception.PromotionRequestNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRequestRepository extends JpaRepository<PromotionRequest, Long> {

    default PromotionRequest getById(Long id) {
        return findById(id).orElseThrow(PromotionRequestNotFoundException::new);
    }
    Optional<PromotionRequest> findById(Long id);
}
