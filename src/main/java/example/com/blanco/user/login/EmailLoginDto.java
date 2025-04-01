package example.com.blanco.user.login;

import example.com.blanco.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailLoginDto {

    @Email(
            message = "Invalid email",
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$\n"
    )
    private String email;

    @Size(
            min = 8,
            max = 24,
            message = "Password length must be between 8 and 24"
    )
    private String password;

    public User toUser() {
        return new User(
                this.getEmail().toLowerCase(),
                this.getPassword()
        );
    }

    @Override
    public String toString() {
        return "{ " + this.getEmail() +
                ", " + this.getPassword() +
                " }";
    }
}
