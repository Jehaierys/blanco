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
public class UserLoginThroughEmail {
    @Email(message = "wrong email")
    private String email;
    @Size(
            min = 8,
            max = 24,
            message = "password length must be between 8 and 24"
    )
    private String password;

    public User toUser() {
        return new User(this.getEmail(), this.getPassword());
    }
//    @Pattern(
//            regexp = "^\\+?\\d{10,15}$",
//            message = "Invalid phone number format"
//    )
//    private String phoneNumber;
}
