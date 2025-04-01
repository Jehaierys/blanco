package example.com.blanco.transactions.transfer;


import example.com.blanco.transactions.transfer.dto.NewTransfer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String processTransfer(@Valid @RequestBody NewTransfer newTransfer) {
        transferService.processTransfer(newTransfer.toTransfer());
        return "ok";
    }

    @GetMapping
    @Cacheable("h")
    public List<Transfer> transferHistory(@RequestParam String cardNumber) {
        return transferService.transferHistory(cardNumber);
    }
}
