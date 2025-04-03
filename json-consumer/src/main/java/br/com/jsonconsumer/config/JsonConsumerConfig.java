package br.com.jsonconsumer.config;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
@RequiredArgsConstructor
public class JsonConsumerConfig {

  private final KafkaProperties properties;

  @Bean
  public ConsumerFactory<String, Object> jsonConsumerFactory() {
    var configs = new HashMap<String, Object>();
    configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
    configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(configs);
  }

  @Bean
  public RecordMessageConverter messageConverter() {
    return new JsonMessageConverter();
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> jsonContainerFactory(
      ConsumerFactory<String, Object> jsonConsumerFactory, RecordMessageConverter messageConverter) {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
    factory.setConsumerFactory(jsonConsumerFactory);
    factory.setRecordMessageConverter(messageConverter);
    return factory;
  }

}
