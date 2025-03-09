package example.com.blanco.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepo;

    public List<User> allUsers() {
        return userRepo.findAll();
    }
}
