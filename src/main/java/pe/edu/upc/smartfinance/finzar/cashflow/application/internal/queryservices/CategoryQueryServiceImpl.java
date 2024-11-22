package pe.edu.upc.smartfinance.finzar.cashflow.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.smartfinance.finzar.cashflow.domain.services.CategoryQueryService;
import pe.edu.upc.smartfinance.finzar.cashflow.infrastructure.persistence.jpa.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public CategoryQueryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return this.categoryRepository.findById(query.categoryId());
    }
}
