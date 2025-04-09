package example.com.blanco.account;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager entityManager;

    /*
    what is it??????
     */
    public Account findByEmail(String email) {
        return entityManager.find(Account.class, email);
    }

    public List<Account> allAccounts() {
        return entityManager.createNativeQuery("select * from accounts", Account.class).getResultList();
    }

    public Account getById(Long id) {
        return entityManager.find(Account.class, id);
    }
}
