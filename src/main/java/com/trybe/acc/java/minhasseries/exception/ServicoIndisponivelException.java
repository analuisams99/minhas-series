package com.trybe.acc.java.minhasseries.exception;

/**Classe ServicoIndisponivelException.*/
@SuppressWarnings("serial")
public class ServicoIndisponivelException extends RuntimeException {
  private static String message = "Serviço temporariamente indisponível";

  public ServicoIndisponivelException() {
    super(message);
  }
}
