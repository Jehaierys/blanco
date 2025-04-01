package example.com.blanco.user;


import example.com.blanco.account.Account;
import example.com.blanco.user.login.EmailLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public String deleteById(Long id) {
        return userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public Long getIdByEmail(String email) {
        return userRepo.getIdByEmail(email);
    }

    public User save(User user) {
        userRepo.save(user);
        return userRepo
                .findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + user.getEmail()));
    }

    public void enableUserById(Long id) {
        userRepo.enableUser(id);
    }

}
