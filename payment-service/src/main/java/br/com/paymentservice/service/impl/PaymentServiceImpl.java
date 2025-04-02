package br.com.paymentservice.service.impl;

import br.com.paymentservice.model.Payment;
import br.com.paymentservice.service.PaymentService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final KafkaTemplate<String, Serializable> kafkaTemplate;

  @SneakyThrows
  @Override
  public void sendPayment(Payment payment) {
    log.info("Recebi o pagamento {}", payment);
    Thread.sleep(1000);
    log.info("Enviando pagamento...");
    kafkaTemplate.send("payment-topic", payment);

  }
}
