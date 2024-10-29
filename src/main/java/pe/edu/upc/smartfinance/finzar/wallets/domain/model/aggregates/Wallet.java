package pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.smartfinance.finzar.wallets.domain.model.commands.CreateWalletCommand;

@Entity
@Table(name = "wallets")
public class Wallet  extends AuditableAbstractAggregateRoot<Wallet> {

    @Getter
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Getter
    @Min(0)
    @Column(name = "balance", nullable = false, columnDefinition = "double default 0")
    private double balance;

    @Getter
    @Min(0)
    @Column(name = "total_balance", nullable = false, columnDefinition = "double default 0")
    private double totalBalance;

    public Wallet() {

    }

    public Wallet(CreateWalletCommand command) {
        this.userId = command.userId();
        this.name = command.name();
        this.balance = command.balance();
        this.totalBalance = command.totalBalance();
    }


}
