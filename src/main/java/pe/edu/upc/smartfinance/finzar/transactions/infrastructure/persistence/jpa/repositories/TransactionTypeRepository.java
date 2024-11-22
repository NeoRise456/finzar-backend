package pe.edu.upc.smartfinance.finzar.transactions.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.entities.TransactionType;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.valueobjects.TransactionTypes;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    boolean existsByName(TransactionTypes name);
    Optional<TransactionType> findByName(TransactionTypes name);
}
