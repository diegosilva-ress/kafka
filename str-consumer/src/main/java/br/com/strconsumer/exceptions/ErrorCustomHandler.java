package br.com.strconsumer.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ErrorCustomHandler implements KafkaListenerErrorHandler {

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
    log.info("Exception handler ::: Capturei um erro");
    log.info("Payload ::: {}", message.getPayload());
    log.info("Headers ::: {}", message.getHeaders());
    log.info("Offset ::: {}", message.getHeaders().get("kafka_offset"));
    log.info("Exception ::: {}", exception.getMessage());
    return null;
  }
}
