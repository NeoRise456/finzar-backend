package pe.edu.upc.smartfinance.finzar.savingsgraphics.infrastructure.persistence.jpa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.savingsgraphics.domain.model.aggregates.SavingsGraphics;

import java.util.List;

public interface SavingsGraphicsRepository extends JpaRepository<SavingsGraphics, Long> {
    List<SavingsGraphics> findAll();
}
