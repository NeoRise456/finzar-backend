package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.commands.DeleteExpenseCommand;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetExpenseByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetExpensesByWalletIdAndCategoryIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.ExpenseCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.ExpenseQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.CreateExpenseResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.ExpenseResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Expenses", description = "Expenses Management Endpoints")
public class ExpenseController {

    private final ExpenseCommandService expenseCommandService;
    private final ExpenseQueryService expenseQueryService;

    public ExpenseController(ExpenseCommandService expenseCommandService, ExpenseQueryService expenseQueryService) {
        this.expenseCommandService = expenseCommandService;
        this.expenseQueryService = expenseQueryService;
    }


    @GetMapping
    public ResponseEntity<List<ExpenseResource>> getExpensesByWalletId(@RequestParam Long walletId, @RequestParam Long categoryId) {

        var getExpensesByWalletIdAndCategoryIdQuery = new GetExpensesByWalletIdAndCategoryIdQuery(walletId, categoryId);

        var expenses = this.expenseQueryService.handle(getExpensesByWalletIdAndCategoryIdQuery);

        if (expenses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var expenseResources = expenses.stream()
                .map(ExpenseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(expenseResources);
    }


    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> getExpenseById( @PathVariable Long expenseId){
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);

        var expense = this.expenseQueryService.handle(getExpenseByIdQuery);

        if (expense.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());

        return ResponseEntity.ok(expenseResource);
    }


    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@RequestBody CreateExpenseResource resource) {
        var createExpenseCommand = CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);

        var expenseId = this.expenseCommandService.handle(createExpenseCommand);

        if (expenseId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = this.expenseQueryService.handle(getExpenseByIdQuery);

        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());

        return new ResponseEntity<>(expenseResource, HttpStatus.CREATED);
    }


    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long expenseId) {
        var deleteExpenseCommand = new DeleteExpenseCommand(expenseId);
        var deleteConfirmation = this.expenseCommandService.handle(deleteExpenseCommand);

        if (!deleteConfirmation) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("The Expense with the given id has been deleted");
    }

}
