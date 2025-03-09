package example.com.blanco.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public String addUser(@RequestBody User user) {
        log.info("A user tried to be added: {}", user);
        return "hello";
    }

    @GetMapping()
    public List<User> getAllUsers() {
        log.info("getAllUsers");
        return userService.allUsers();
    }
}
