package br.com.strconsumer.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class StringConsumerConfig {

  private final KafkaProperties properties;

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    var configs = new HashMap<String, Object>();
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(configs);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> strContainerFactory(
      ConsumerFactory<String, String> consumerFactory) {

    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> validateMessageContainerFactory(
      ConsumerFactory<String, String> consumerFactory) {

    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory);
    factory.setRecordInterceptor(validateMessage());
    return factory;
  }

  private RecordInterceptor<String, String> validateMessage() {
    return (record, consumer) -> {
      if (record.value().contains("teste")) {
        log.info("Possui a palavra teste");
        return record;
      }
      return record;
    };
  }

}
