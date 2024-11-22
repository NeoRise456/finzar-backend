package pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.PeriodRecurrence;

public interface PeriodRecurrenceRepository extends JpaRepository<PeriodRecurrence, Long> {
}
