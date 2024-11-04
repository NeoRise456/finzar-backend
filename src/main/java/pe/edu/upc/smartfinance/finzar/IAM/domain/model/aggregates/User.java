package pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.smartfinance.finzar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Getter
    @NotNull
    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    public User () {}

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User updateInformation(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        return this;
    }

}
