package example.com.blanco.account;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import example.com.blanco.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cardNumber")
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
    @SequenceGenerator(name = "account_seq_gen", sequenceName = "account_seq", allocationSize = 1)
    private Integer id;
    @OneToOne(
            mappedBy = "account"
    )
    @JsonManagedReference
    private User user;
    @Embedded
    private Card card;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Account(User user) {
        this.setBalance(BigDecimal.ZERO);
        this.setUser(user);
        this.setCurrency(Currency.TENGE);

        this.setCard(new Card());
    }
}
