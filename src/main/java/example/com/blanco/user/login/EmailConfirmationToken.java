package example.com.blanco.user.login;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Document(
        collection = "email_confirmation_tokens",
        language = "english"
)
public class EmailConfirmationToken {

    @Id
    private UUID id;

    @Field("user_id")
    private Long userId;

    @Field("token")
    private String token;

    @Field("expiry_date")
    private LocalDate expiryDate;

    public EmailConfirmationToken() {
        this.id = UUID.randomUUID();
    }
}
