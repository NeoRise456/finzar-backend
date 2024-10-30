package pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndUserIdIs( String name, Long userId);
    Optional<Wallet> findByName(String name);
}
