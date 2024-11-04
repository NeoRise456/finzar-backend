package pe.edu.upc.smartfinance.finzar.savings.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.CreateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.DeleteSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.UpdateSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByNameQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingCommandService;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.SavingResource;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform.SavingResourceFromEntityAssembler;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SavingsContextFacade {

    private final SavingCommandService savingCommandService;
    private final SavingQueryService savingQueryService;

    public SavingsContextFacade(SavingCommandService savingCommandService, SavingQueryService savingQueryService) {
        this.savingCommandService = savingCommandService;
        this.savingQueryService = savingQueryService;
    }

    public Optional<SavingResource> fetchSavingById(Long savingId) {
        var getSavingByIdQuery = new GetSavingByIdQuery(savingId);
        var optionalSaving = savingQueryService.handle(getSavingByIdQuery);
        if (optionalSaving.isEmpty()) {
            return Optional.empty();
        }
        var savingResource = SavingResourceFromEntityAssembler.toResourceFromEntity(optionalSaving.get());
        return Optional.of(savingResource);
    }

    public Long fetchSavingIdByName(String name) {
        var getSavingByNameQuery = new GetSavingByNameQuery(name);
        var optionalSaving = savingQueryService.handle(getSavingByNameQuery);
        if (optionalSaving.isEmpty()) {
            return 0L;
        }
        return optionalSaving.get().getId();
    }

    public boolean existsSavingByNameAndIdIsNot(String name, Long id) {
        var getSavingByNameQuery = new GetSavingByNameQuery(name);
        var optionalSaving = savingQueryService.handle(getSavingByNameQuery);
        if (optionalSaving.isEmpty()) {
            return false;
        }
        return !optionalSaving.get().getId().equals(id);
    }

    public Long createSaving(Long userId, String name, double totalGoal, double currentAmount, Long categoryId, LocalDate startDate, LocalDate endDate) {
        var createSavingCommand = new CreateSavingCommand(userId, name, totalGoal, currentAmount, categoryId, startDate, endDate);
        var savingId = savingCommandService.handle(createSavingCommand);
        if (savingId == null) {
            return 0L;
        }
        return savingId;
    }

    public Long updateSaving(Long savingId, String name, double totalGoal, double currentAmount, Long categoryId, LocalDate startDate, LocalDate endDate) {
        var updateSavingCommand = new UpdateSavingCommand(savingId, name, totalGoal, currentAmount, categoryId, startDate, endDate);
        var optionalSaving = savingCommandService.handle(updateSavingCommand);
        if (optionalSaving.isEmpty()) {
            return 0L;
        }
        return optionalSaving.get().getId();
    }

    public void deleteSaving(Long savingId) {
        var deleteSavingCommand = new DeleteSavingCommand(savingId);
        savingCommandService.handle(deleteSavingCommand);
    }
}