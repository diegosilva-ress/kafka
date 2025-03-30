package br.com.strproducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class StringProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void send(String message) {
    log.info("Sending message: {}", message);
    kafkaTemplate.send("str-topic", message);
//        .whenComplete((record, ex) -> {
//      if (ex == null) {
//        log.info("Send message with success: {}", message);
//        log.info("Partition {}", record.getRecordMetadata().partition());
//        log.info("Offset {}", record.getRecordMetadata().offset());
//      } else {
//        log.error("Send message with error: {}", ex.getMessage());
//      }
//    });
  }

}
