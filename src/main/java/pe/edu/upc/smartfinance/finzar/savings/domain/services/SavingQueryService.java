package pe.edu.upc.smartfinance.finzar.savings.domain.services;

import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;

import java.util.List;
import java.util.Optional;

public interface SavingQueryService {
    List<Saving> getAllSavings();
    Optional<Saving> getSavingById(Long id);
    Saving createSaving(Saving saving);
    Saving updateSaving(Long id, Saving saving);
    void deleteSaving(Long id);
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
    Optional<Saving> findByName(String name);
}