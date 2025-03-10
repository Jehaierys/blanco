package example.com.blanco.account;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class Card {

    public Card() {
        Random random = new Random();
        random.nextInt(100, 1000);
        this.cvv = random.toString();
    }

    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
