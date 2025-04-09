package example.com.blanco.account;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;

    public Account getById(Long id) {
        return accountRepo.getById(id);
    }

    public List<Account> allAccounts() {
        return accountRepo.allAccounts();
    }
}
