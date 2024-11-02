package pe.edu.upc.smartfinance.finzar.wallets.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateCategoryCommand;

@Entity
@Table(name = "categories")
public class Category extends AuditableAbstractAggregateRoot<Category> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    public Category() {
        this.name = "";
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Category(CreateCategoryCommand command) {
        this.name = command.name();
        this.description = command.description();
    }




}
