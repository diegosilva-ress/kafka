package br.com.strproducer.resources;

import br.com.strproducer.services.StringProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/producer")
@RequiredArgsConstructor
public class StringProducerResource {

  private final StringProducerService producerService;

  @PostMapping
  public ResponseEntity<?> sendMessage(@RequestBody String message) {
    producerService.send(message);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
