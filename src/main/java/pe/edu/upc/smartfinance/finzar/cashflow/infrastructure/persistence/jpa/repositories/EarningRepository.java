package pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.aggregates.Earning;

import java.util.List;

public interface EarningRepository extends JpaRepository<Earning, Long> {
    List<Earning> findEarningsByWallet_IdAndCategory_Id(Long walletId, Long categoryId);
}
