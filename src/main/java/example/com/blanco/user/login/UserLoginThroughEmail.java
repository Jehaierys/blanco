package example.com.blanco.user.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserLogin {
    @Email(message = "wrong email")
    private String email;
    @Size(
            min = 8,
            max = 24,
            message = "password length must be between 8 and 24"
    )
    private String password;
    @Pattern(
            regexp = "^\\+?\\d{10,15}$",
            message = "Invalid phone number format"
    )
    private String phoneNumber;
}
