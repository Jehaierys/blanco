package example.com.blanco.transactions.transfer;


import example.com.blanco.account.Account;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TransferRepo {

    private final EntityManager entityManager;

    public Account accountByCardNumber(String cardNumber) {
        return (Account) entityManager.createNativeQuery(
                "SELECT * FROM accounts WHERE card_number = ?",
                Account.class)
                .setParameter(1, cardNumber)
                .getSingleResult();
    }

    public void writeOff(Long accountId, BigDecimal amount) {
        entityManager.createNativeQuery(
                        "UPDATE accounts SET balance = balance - ? WHERE id = ?"
                )
                .setParameter(1, amount)
                .setParameter(2, accountId)
                .executeUpdate();
    }

    public void topUp(Long id, BigDecimal amount) {
        entityManager.createNativeQuery(
                        "UPDATE accounts SET balance = balance + ? WHERE id = ?"
                )
                .setParameter(1, amount)
                .setParameter(2, id)
                .executeUpdate();
    }


    public List<Transfer> transfers(String cardNumber) {
        return entityManager.createNativeQuery(
                        "SELECT * FROM transfers WHERE receiver = ? OR sender = ?",
                        Transfer.class
                )
                .setParameter(1, cardNumber)
                .setParameter(2, cardNumber)
                .getResultList();
    }

    public boolean cardExists(String cardNumber) {
        return entityManager.createNativeQuery("SELECT true FROM accounts WHERE card_number = ?")
                .setParameter(1, cardNumber)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null) != null;
    }

}
