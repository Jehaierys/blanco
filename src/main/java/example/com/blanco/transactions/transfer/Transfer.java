package example.com.blanco.transactions.transfer;


import example.com.blanco.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "transfer",
        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "")
        }
)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_seq_gen")
    @SequenceGenerator(
            name = "transfer_seq_gen",
            sequenceName = "transfer_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    private String sender;

    private String receiver;

    private BigDecimal amount;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timestamp;

    private float commission;
}
