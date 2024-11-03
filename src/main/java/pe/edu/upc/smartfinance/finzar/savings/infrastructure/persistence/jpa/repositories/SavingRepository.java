package pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
    Optional<Saving> findByName(String name);
    List<Saving> findSavingsByUserId(UserId userId);

}

