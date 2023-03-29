package com.trybe.acc.java.minhasseries.exception;

/**Classe SerieExistenteException.*/
@SuppressWarnings("serial")
public class SerieExistenteException extends RuntimeException {
  private static String message = "Série Existente";

  public SerieExistenteException() {
    super(message);
  }
}
