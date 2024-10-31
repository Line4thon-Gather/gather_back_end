package org.example.gather_back_end.repository;

import java.util.Optional;
import org.example.gather_back_end.domain.Portfolio;
import org.example.gather_back_end.portfolio.exception.PortfolioNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    default Portfolio getById(Long id) {
        return findById(id).orElseThrow(PortfolioNotFoundException::new);
    }

    Optional<Portfolio> findById(Long id);
}
