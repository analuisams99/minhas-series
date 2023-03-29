package com.trybe.acc.java.minhasseries.exception;

/**Classe SerieNaoEncontradaException.*/
@SuppressWarnings("serial")
public class SerieNaoEncontradaException extends RuntimeException {
  private static String message = "Série não encontrada";

  public SerieNaoEncontradaException() {
    super(message);
  }
}
