package pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.PeriodRecurrence;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.valueobjects.PeriodRecurrences;

import java.util.Optional;

@Repository
public interface PeriodRecurrenceRepository extends JpaRepository<PeriodRecurrence, Long> {
    boolean existsByName(PeriodRecurrences name);
    Optional<PeriodRecurrence> findByName(PeriodRecurrences name);
}
