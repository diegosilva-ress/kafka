package br.com.paymentservice.service.impl;

import br.com.paymentservice.model.Payment;
import br.com.paymentservice.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

  @Override
  public void sendPayment(Payment payment) {
    log.info("PaymentServiceImpl ::: Recebi o pagamento {}", payment);

  }
}
