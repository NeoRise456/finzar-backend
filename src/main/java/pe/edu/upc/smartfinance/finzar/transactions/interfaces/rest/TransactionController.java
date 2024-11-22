package pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.model.queries.GetTransactionsByWalletIdQuery;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionCommandService;
import pe.edu.upc.smartfinance.finzar.transactions.domain.services.TransactionQueryService;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.CreateTransactionToWalletResource;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.resources.TransactionResource;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform.CreateTransactionToWalletCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.transactions.interfaces.rest.transform.TransactionResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Transactions Management Endpoints")
public class TransactionController {
    private final TransactionCommandService transactionCommandService;
    private final TransactionQueryService transactionQueryService;

    public TransactionController(TransactionCommandService transactionCommandService,
                                 TransactionQueryService transactionQueryService) {
        this.transactionCommandService = transactionCommandService;
        this.transactionQueryService = transactionQueryService;
    }

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<TransactionResource>> getTransactionsByWalletId(@PathVariable Long walletId){
        var getTransactionsByWalletIdQuery = new GetTransactionsByWalletIdQuery(walletId);

        var transactions = this.transactionQueryService.handle(getTransactionsByWalletIdQuery);

        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var transactionResources = transactions.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }


    @PostMapping
    public ResponseEntity<TransactionResource> createTransactionToWallet(@RequestBody CreateTransactionToWalletResource resource) {

        var createTransactionToWalletCommand = CreateTransactionToWalletCommandFromResourceAssembler.toCommandFromResource(resource);

        var transactionId = this.transactionCommandService.handle(createTransactionToWalletCommand);

        if (transactionId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);

        var transaction = this.transactionQueryService.handle(getTransactionByIdQuery);

        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());

        return new ResponseEntity<>(transactionResource, HttpStatus.CREATED);
    }


    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long transactionId) {
        var deleteTransactionCommand = new DeleteTransactionCommand(transactionId);
        var deleteConfirmation = this.transactionCommandService.handle(deleteTransactionCommand);

        if (!deleteConfirmation){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("The Transaction with the given id has been deleted");

    }
}
