package pe.edu.upc.smartfinance.finzar.wallets.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.iam.domain.model.aggregates.User;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "wallets")
public class Wallet  extends AuditableAbstractAggregateRoot<Wallet> {


    @Getter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Getter
    @Min(0)
    @Column(name = "balance", nullable = false, columnDefinition = "real default 0")
    private Double balance;


    public Wallet() {

    }

    public Wallet(User user,String name, Double balance) {
        this();
        this.user = user;
        this.name = name;
        this.balance = balance;
    }

    public Wallet updateInformation(String name,Double balance) {
        this.name = name;
        this.balance = balance;
        return this;
    }



}
