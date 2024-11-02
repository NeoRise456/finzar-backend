package pe.edu.upc.smartfinance.finzar.IAM.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
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
