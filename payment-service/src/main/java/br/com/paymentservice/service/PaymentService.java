package br.com.paymentservice.service;

import br.com.paymentservice.model.Payment;

public interface PaymentService {

  void sendPayment(Payment payment);

}
