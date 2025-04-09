package example.com.blanco.account;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Account findById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    // TODO delete later
    @GetMapping()
    public List<Account> accounts() {
        return accountService.allAccounts();
    }
}
