package pe.edu.upc.smartfinance.finzar.savings.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.aggregates.Saving;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.CreateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.DeleteSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.UpdateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingCommandService;
import pe.edu.upc.smartfinance.finzar.savings.infrastructure.persistence.jpa.repositories.SavingRepository;

import java.util.Optional;

@Service
public class SavingCommandServiceImpl implements SavingCommandService {

    private final SavingRepository savingRepository;

    public SavingCommandServiceImpl(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    @Override
    public Long handle(CreateSavingCommand command) {
        var name = command.name();
        if (this.savingRepository.existsByName(name)) {
            throw new IllegalArgumentException("Saving with name " + name + " already exists");
        }

        var saving = new Saving(command);
        try {
            this.savingRepository.save(saving);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving: " + e.getMessage());
        }
        return saving.getId();
    }

    @Override
    public Optional<Saving> handle(UpdateSavingCommand command) {
        var savingId = command.savingId();
        var name = command.name();

        if (this.savingRepository.existsByNameAndIdIsNot(name, savingId)) {
            throw new IllegalArgumentException("Saving with name " + name + " already exists");
        }

        var savingToUpdate = this.savingRepository.findById(savingId)
                .orElseThrow(() -> new IllegalArgumentException("Saving with id " + savingId + " does not exist"));

        savingToUpdate.updateInformation(command.name(), command.totalGoal(),
                command.currentAmount(), command.categoryId(),
                command.startDate(), command.endDate());

        try {
            var updatedSaving = this.savingRepository.save(savingToUpdate);
            return Optional.of(updatedSaving);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating saving: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteSavingCommand command) {
        var savingId = command.savingId();

        if (!this.savingRepository.existsById(savingId)) {
            throw new IllegalArgumentException("Saving with id " + savingId + " does not exist");
        }

        try {
            this.savingRepository.deleteById(savingId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting saving: " + e.getMessage());
        }
    }
}