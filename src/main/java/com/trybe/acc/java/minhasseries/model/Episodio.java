package com.trybe.acc.java.minhasseries.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**Classe de entidade Episodio.*/
@Entity
public class Episodio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private Integer numero;
  private Integer duracaoEmMinutos;
  
  @ManyToOne
  private Serie serie;
  
  public Episodio() {}

  /**Método principal com 2 parâmetros.*/
  public Episodio(Integer numero, Integer duracaoEmMinutos) {
    this.numero = numero;
    this.duracaoEmMinutos = duracaoEmMinutos;
  }
  
  /**Método principal com todos os parâmetros.*/
  public Episodio(Long id, Integer numero, Integer duracaoEmMinutos, Serie serie) {
    this.numero = numero;
    this.duracaoEmMinutos = duracaoEmMinutos;
    this.serie = serie;
  }

  public Long getId() {
    return id;
  }
  
  public Integer getNumero() {
    return numero;
  }
  
  public void setNumero(Integer numero) {
    this.numero = numero;
  }
  
  public Integer getDuracaoEmMinutos() {
    return duracaoEmMinutos;
  }
  
  public void setDuracaoEmMinutos(Integer duracaoEmMinutos) {
    this.duracaoEmMinutos = duracaoEmMinutos;
  }
  
  public Serie getSerie() {
    return serie;
  }
  
  public void setSerie(Serie serie) {
    this.serie = serie;
  }
}
