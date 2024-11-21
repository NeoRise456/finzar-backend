package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteEarningCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetEarningByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetEarningsByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.EarningCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.EarningQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.CreateEarningResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.EarningResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.CreateEarningCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.EarningResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/earnings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Earnings", description = "Earnings Management Endpoints")
public class EarningController {

    private final EarningCommandService earningCommandService;
    private final EarningQueryService earningQueryService;

    public EarningController(EarningCommandService earningCommandService, EarningQueryService earningQueryService) {
        this.earningCommandService = earningCommandService;
        this.earningQueryService = earningQueryService;
    }


    @GetMapping
    public ResponseEntity<List<EarningResource>> getEarningsByWalletIdAndCategory(@RequestParam Long walletId, @RequestParam Long categoryId) {

        var getEarningsByWalletIdQuery = new GetEarningsByWalletIdAndCategoryIdQuery(walletId,categoryId);

        var earnings = this.earningQueryService.handle(getEarningsByWalletIdQuery);

        if (earnings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var earningResources = earnings.stream()
                .map(EarningResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(earningResources);
    }


    @GetMapping("/{walletId}")
    public ResponseEntity<EarningResource> getEarningsByWalletId(@PathVariable Long walletId) {
        var getEarningsByWalletIdQuery = new GetEarningByIdQuery(walletId);

        var earnings = this.earningQueryService.handle(getEarningsByWalletIdQuery);

        if (earnings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var earningResource = EarningResourceFromEntityAssembler.toResourceFromEntity(earnings.get());
        return ResponseEntity.ok(earningResource);
    }

    @PostMapping
    public ResponseEntity<EarningResource> createEarning(@RequestBody CreateEarningResource resource) {
        var createEarningCommand = CreateEarningCommandFromResourceAssembler.toCommandFromResource(resource);
        var earningId = earningCommandService.handle(createEarningCommand);

        if (earningId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getEarningByIdQuery = new GetEarningByIdQuery(earningId);
        var earning = this.earningQueryService.handle(getEarningByIdQuery);

        var earningResource = EarningResourceFromEntityAssembler.toResourceFromEntity(earning.get());
        return new ResponseEntity<>(earningResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{earningId}")
    public ResponseEntity<?> deleteEarningById(@PathVariable Long earningId) {
        var deleteEarningCommand = new DeleteEarningCommand(earningId);
        var deleteConfirmation = this.earningCommandService.handle(deleteEarningCommand);

        if (!deleteConfirmation){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("The Earning with the given id has been deleted");

    }


}
