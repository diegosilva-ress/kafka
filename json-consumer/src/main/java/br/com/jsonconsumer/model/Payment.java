package br.com.jsonconsumer.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Payment implements Serializable {

  private Long id;
  private Long idUser;
  private Long idProduct;
  private Long cardNumber;

}
