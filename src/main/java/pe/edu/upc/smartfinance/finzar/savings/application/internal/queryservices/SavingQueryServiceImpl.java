package pe.edu.upc.smartfinance.finzar.savings.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories.SavingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SavingQueryServiceImpl implements SavingQueryService {

    private final SavingRepository savingRepository;

    @Autowired
    public SavingQueryServiceImpl(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    @Override
    public List<Saving> getAllSavings() {
        return savingRepository.findAll();
    }

    @Override
    public Optional<Saving> getSavingById(Long id) {
        return savingRepository.findById(id);
    }

    @Override
    public Saving createSaving(Saving saving) {
        return savingRepository.save(saving);
    }

    @Override
    public Saving updateSaving(Long id, Saving saving) {
        return savingRepository.save(saving);
    }

    @Override
    public void deleteSaving(Long id) {
        savingRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return savingRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdIsNot(String name, Long id) {
        return savingRepository.existsByNameAndIdIsNot(name, id);
    }

    @Override
    public Optional<Saving> findByName(String name) {
        return savingRepository.findByName(name);
    }
}