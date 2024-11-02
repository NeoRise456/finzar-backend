package pe.edu.upc.smartfinance.finzar.wallets.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities.Category;
import pe.edu.upc.smartfinance.finzar.wallets.domain.services.CategoryCommandService;
import pe.edu.upc.smartfinance.finzar.wallets.infrastructure.persistence.jpa.repositories.CategoryRepository;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    public CategoryCommandServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Long handle(CreateCategoryCommand command) {
        if (this.categoryRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Category "+ command.name() +" already exists");
        }

        var category = new Category(command);
        this.categoryRepository.save(category);
        return category.getId();
    }
}
