package pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.transform;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.CategoryCommandService;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.CategoryQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CategoryResource;
import pe.edu.upc.smartfinance.finzar.cashflow.interfaces.rest.resources.CreateCategoryResource;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Categories", description = "Categories Management Endpoints")
public class CategoryController {

    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryCommandService categoryCommandService, CategoryQueryService categoryQueryService) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResource> createCategory(@RequestBody CreateCategoryResource resource) {
        var createCategoryCommand = CreateCategoryCommandFromResourceAssembler.toCommandFromResource(resource);
        var categoryId = categoryCommandService.handle(createCategoryCommand);

        if (categoryId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getCategoryByIdQuery = new GetCategoryByIdQuery(categoryId);
        var category = this.categoryQueryService.handle(getCategoryByIdQuery);

        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(category.get());
        return new ResponseEntity<>(categoryResource, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResource> getCategoryById(@PathVariable Long categoryId) {
        var getCategoryByIdQuery = new GetCategoryByIdQuery(categoryId);
        var category = this.categoryQueryService.handle(getCategoryByIdQuery);

        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(category.get());
        return ResponseEntity.ok(categoryResource);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResource>> getAllCategories() {
        var getAllCategoriesQuery = new GetAllCategoriesQuery();
        var categories = this.categoryQueryService.handle(getAllCategoriesQuery);
        var categoryResources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResources);
    }

}
