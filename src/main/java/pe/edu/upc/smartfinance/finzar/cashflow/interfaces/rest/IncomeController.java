package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteIncomeCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomeByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetIncomesByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.IncomeQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CreateIncomeResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.IncomeResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.IncomeResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/incomes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Incomes", description = "Incomes Management Endpoints")
public class IncomeController {

    private final IncomeCommandService incomeCommandService;
    private final IncomeQueryService earningQueryService;

    public IncomeController(IncomeCommandService earningCommandService, IncomeQueryService earningQueryService) {
        this.incomeCommandService = earningCommandService;
        this.earningQueryService = earningQueryService;
    }


    @GetMapping
    public ResponseEntity<List<IncomeResource>> getIncomesByWalletIdAndCategory(@RequestParam Long walletId, @RequestParam Long categoryId) {

        var getEarningsByWalletIdQuery = new GetIncomesByWalletIdAndCategoryIdQuery(walletId,categoryId);

        var incomes = this.earningQueryService.handle(getEarningsByWalletIdQuery);

        if (incomes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var incomesResources = incomes.stream()
                .map(IncomeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(incomesResources);
    }


    //TODO: Implement getIncomesByWalletId
    @GetMapping("/{walletId}")
    public ResponseEntity<IncomeResource> getIncomesByWalletId(@PathVariable Long walletId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@RequestBody CreateIncomeResource resource) {
        var createEarningCommand = CreateIncomeCommandFromResourceAssembler.toCommandFromResource(resource);
        var incomeId = incomeCommandService.handle(createEarningCommand);

        if (incomeId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getEarningByIdQuery = new GetIncomeByIdQuery(incomeId);
        var income = this.earningQueryService.handle(getEarningByIdQuery);

        var incomeResource = IncomeResourceFromEntityAssembler.toResourceFromEntity(income.get());
        return new ResponseEntity<>(incomeResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<?> deleteIncomeById(@PathVariable Long incomeId) {
        var deleteIncomeCommand = new DeleteIncomeCommand(incomeId);
        var deleteConfirmation = this.incomeCommandService.handle(deleteIncomeCommand);

        if (!deleteConfirmation){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("The Income with the given id has been deleted");

    }


}
