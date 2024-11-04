package pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsById(Long id);
    boolean existsByNameAndUserId(String name, Long userId);
    Optional<Wallet> findById(Long id);
    List<Wallet> findWalletsByUser_Id(Long userId);
}
