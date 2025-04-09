package example.com.blanco.user.login;


import example.com.blanco.account.Account;
import example.com.blanco.user.User;
import example.com.blanco.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final EmailConfirmationService emailConfirmationService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User login(@RequestBody @Valid EmailLoginDto dto) {

        log.info("Validated dto: {}", dto.toString());

        dto.setPassword(passwordEncoder.encode((dto.getPassword())));
        User user = dto.toUser();
        user.setAccount(new Account());
        userService.save(user);

        EmailConfirmationToken token = new EmailConfirmationToken();
        token.setToken(passwordEncoder.encode(dto.getEmail() + dto.getPassword()));
        token.setExpiryDate(LocalDate.now().plus(1, ChronoUnit.DAYS));
        token.setUserId(userService.getIdByEmail(dto.getEmail()));
        emailConfirmationService.saveToken(token);

        try {
            emailConfirmationService.sendEmailConfirmationToken(dto.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /*
        accepts user request with confirmation token

        checks whether it is not expired and valid

        and enables user
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/confirm")
    public void confirmEmail(
            @RequestBody String token
    ) {
        EmailConfirmationToken confirmationToken = emailConfirmationService.findToken(token);
        if (
                confirmationToken != null
                && confirmationToken.getToken().equals(token)
                && confirmationToken.getExpiryDate().isBefore(LocalDate.now())
        ) {
            // TODO: what if the field enable already were true
            userService.enableUserById(confirmationToken.getUserId());
            // TODO: what if
            emailConfirmationService.deleteToken(confirmationToken);
        }
    }

    @DeleteMapping("/token/{id}")
    public String tokenByUserId(@PathVariable Long id) {
        log.info("HEEEREE must be user id: " + id);
        return emailConfirmationService.getConfirmationTokenByUserId(id);
    }
}
