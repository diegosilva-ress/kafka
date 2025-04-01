package br.com.paymentservice.resource.impl;

import br.com.paymentservice.model.Payment;
import br.com.paymentservice.resource.PaymentResource;
import br.com.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentResourceImpl implements PaymentResource {

  private final PaymentService paymentService;

  @Override
  public ResponseEntity<Payment> payment(Payment payment) {
    paymentService.sendPayment(payment);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
