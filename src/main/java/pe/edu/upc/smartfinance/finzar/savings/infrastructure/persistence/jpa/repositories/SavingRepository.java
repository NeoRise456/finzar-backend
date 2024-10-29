package pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;

import java.util.Optional;

public interface SavingRepository extends JpaRepository<Saving, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
    Optional<Saving> findByName(String name);
}

