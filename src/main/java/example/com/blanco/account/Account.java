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
    @SequenceGenerator(
            schema = "public",
            name = "account_seq_gen",
            sequenceName = "account_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

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

    public Account() {
        this.setBalance(BigDecimal.ZERO);
        this.setCurrency(Currency.TENGE);

        this.setCard(new Card());
    }
}
