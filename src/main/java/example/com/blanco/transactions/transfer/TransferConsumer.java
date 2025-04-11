package example.com.blanco.transactions.transfer;

import example.com.blanco.transactions.transfer.dto.NewTransfer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferConsumer {

    private final TransferService transferService;

    @KafkaListener(topics = "transfer", groupId = "transfer-group")
    public void listen(NewTransfer newTransfer,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp) {
        Transfer transfer = newTransfer.toTransfer();
        transfer.setTimestamp(Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault()));
        log.info(transfer.toString());
        transferService.processTransfer(transfer);
    }
}
