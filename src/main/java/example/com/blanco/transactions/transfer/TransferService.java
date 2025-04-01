package example.com.blanco.transactions.transfer;


import example.com.blanco.account.Account;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepo transferRepo;


    /*
        validates both cards

        checks the sender balance

        writes off from sender

        tops up receiver
     */
    @Transactional
    public void processTransfer(Transfer transfer) {
        Account receiver = transferRepo.accountByCardNumber(transfer.getReceiver());
        Account sender = transferRepo.accountByCardNumber(transfer.getSender());

        if (receiver != null &&
                sender != null &&
                sender.getBalance().subtract(transfer.getAmount()).compareTo(BigDecimal.ZERO) >= 0) {
            throw new RuntimeException(); // TODO
        }

        transferRepo.writeOff(sender.getId(), transfer.getAmount());
        transferRepo.topUp(receiver.getId(), transfer.getAmount());
    }

    public List<Transfer> transferHistory(String cardNumber) {
        if (!transferRepo.cardExists(cardNumber)) {
            throw new RuntimeException();  // TODO
        }
        return transferRepo.transfers(cardNumber);
    }
}
