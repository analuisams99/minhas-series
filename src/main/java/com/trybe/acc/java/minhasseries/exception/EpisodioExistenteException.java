package com.trybe.acc.java.minhasseries.exception;

/**Classe EpisodioExistenteException.*/
@SuppressWarnings("serial")
public class EpisodioExistenteException extends RuntimeException {
  private static String message = "Episódio Existente";
  
  public EpisodioExistenteException() {
    super(message);
  }
}
