package example.com.blanco.account;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class Card {

    public Card() {
        Random random = new Random();
        this.cvv = random.nextInt(100, 1000);

        this.expiryDate = LocalDate.now()
                .plus(3, ChronoUnit.YEARS)
                .format(DateTimeFormatter.ofPattern("yy MM"));

        this.cardNumber = UUID.randomUUID().toString().toUpperCase();
    }

    private String cardNumber;
    private String expiryDate;
    private int cvv;

    @Override
    public String toString() {
        return "{ number: " + cardNumber +
                ", expiry date: " + expiryDate +
                ", cvv: " + cvv + " }";
    }
}
