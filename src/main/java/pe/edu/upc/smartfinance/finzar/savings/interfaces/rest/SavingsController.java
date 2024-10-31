package pe.edu.upc.smartfinance.finzar.savings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.commands.DeleteSavingCommand;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetAllSavingsQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.model.queries.GetSavingByIdQuery;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingCommandService;
import pe.edu.upc.smartfinance.finzar.savings.domain.services.SavingQueryService;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.CreateSavingResource;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.resources.SavingResource;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform.CreateSavingCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform.SavingResourceFromEntityAssembler;
import pe.edu.upc.smartfinance.finzar.savings.interfaces.rest.transform.UpdateSavingCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/savings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Savings", description = "Savings Management Endpoints")
public class SavingsController {

    private final SavingQueryService savingQueryService;
    private final SavingCommandService savingCommandService;

    public SavingsController(SavingQueryService savingQueryService, SavingCommandService savingCommandService) {
        this.savingQueryService = savingQueryService;
        this.savingCommandService = savingCommandService;
    }

    @PostMapping
    public ResponseEntity<SavingResource> createSaving(@RequestBody CreateSavingResource resource) {
        var createSavingCommand = CreateSavingCommandFromResourceAssembler.toCommandFromResource(resource);
        var savingId = this.savingCommandService.handle(createSavingCommand);

        if (savingId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getSavingByIdQuery = new GetSavingByIdQuery(savingId);
        var optionalSaving = this.savingQueryService.handle(getSavingByIdQuery);

        var savingResource = SavingResourceFromEntityAssembler.toResourceFromEntity(optionalSaving.get());
        return new ResponseEntity<>(savingResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SavingResource>> getAllSavings() {
        var getAllSavingsQuery = new GetAllSavingsQuery();
        var savings = this.savingQueryService.handle(getAllSavingsQuery);
        var savingResources = savings.stream()
                .map(SavingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(savingResources);
    }

    @GetMapping("/{savingId}")
    public ResponseEntity<SavingResource> getSavingById(@PathVariable Long savingId) {
        var getSavingByIdQuery = new GetSavingByIdQuery(savingId);
        var optionalSaving = this.savingQueryService.handle(getSavingByIdQuery);
        if (optionalSaving.isEmpty())
            return ResponseEntity.badRequest().build();
        var savingResource = SavingResourceFromEntityAssembler.toResourceFromEntity(optionalSaving.get());
        return ResponseEntity.ok(savingResource);
    }

    @PutMapping("/{savingId}")
    public ResponseEntity<SavingResource> updateSaving(@PathVariable Long savingId, @RequestBody SavingResource resource) {
        var updateSavingCommand = UpdateSavingCommandFromResourceAssembler.toCommandFromResource(savingId, resource);
        var optionalSaving = this.savingCommandService.handle(updateSavingCommand);

        if (optionalSaving.isEmpty())
            return ResponseEntity.badRequest().build();
        var savingResource = SavingResourceFromEntityAssembler.toResourceFromEntity(optionalSaving.get());
        return ResponseEntity.ok(savingResource);
    }

    @DeleteMapping("/{savingId}")
    public ResponseEntity<?> deleteSaving(@PathVariable Long savingId) {
        var deleteSavingCommand = new DeleteSavingCommand(savingId);
        this.savingCommandService.handle(deleteSavingCommand);
        return ResponseEntity.noContent().build();
    }
}