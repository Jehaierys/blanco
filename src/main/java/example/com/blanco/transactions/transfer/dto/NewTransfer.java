package example.com.blanco.transactions.transfer.dto;

import example.com.blanco.transactions.transfer.Transfer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewTransfer {
    @Length(
            message = "Bad receiver input",
            min = 12,
            max = 12
    )
    private String receiver;

    @Length(
            message = "Bad sender input",
            min = 12,
            max = 12
    )
    private String sender;

    @NotNull
    @Positive
    private BigDecimal amount;

    public Transfer toTransfer() {
        return Transfer.builder()
                .receiver(receiver)
                .sender(sender)
                .amount(amount)
                .build();
    }
}
