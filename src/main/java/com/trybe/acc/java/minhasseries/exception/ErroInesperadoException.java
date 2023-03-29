package com.trybe.acc.java.minhasseries.exception;

/**Classe ErroInesperadoException.*/
@SuppressWarnings("serial")
public class ErroInesperadoException extends RuntimeException {
  private static String message = "Erro inesperado";

  public ErroInesperadoException() {
    super(message);
  }
}
