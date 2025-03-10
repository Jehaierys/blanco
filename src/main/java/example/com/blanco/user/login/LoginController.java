package example.com.blanco.user.login;


import example.com.blanco.user.User;
import example.com.blanco.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @PostMapping
    public User login(@RequestBody @Valid UserLoginThroughEmail dto) {
        return userService.save(dto.toUser());
    }
}
