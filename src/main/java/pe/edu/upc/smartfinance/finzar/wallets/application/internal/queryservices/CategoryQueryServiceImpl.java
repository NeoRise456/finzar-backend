package pe.edu.upc.smartfinance.finzar.wallets.application.internal.queryservices;

import pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.queries.GetCategoryByIdQuery;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.CategoryQueryService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

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
