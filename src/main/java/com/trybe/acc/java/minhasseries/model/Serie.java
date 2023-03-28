package com.trybe.acc.java.minhasseries.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**Classe de entidade Serie.*/
@Entity
public class Serie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  
  @Column
  @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
  private List<Episodio> episodios = new ArrayList<>();
  
  public Serie() {}

  public Serie(String nome) {
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public List<Episodio> getEpisodios() {
    return episodios;
  }
  
  public void adicionarEpisodio(Episodio episodios) {
    this.episodios.add(episodios);
  }
  
  public Integer tempoGastoTotalEmMinutos() {
    return episodios.stream().mapToInt(ep -> ep.getDuracaoEmMinutos()).sum();
  }
}
