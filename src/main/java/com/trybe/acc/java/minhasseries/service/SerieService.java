package com.trybe.acc.java.minhasseries.service;

import com.trybe.acc.java.minhasseries.exception.EpisodioExistenteException;
import com.trybe.acc.java.minhasseries.exception.SerieExistenteException;
import com.trybe.acc.java.minhasseries.exception.SerieNaoEncontradaException;
import com.trybe.acc.java.minhasseries.exception.ServicoIndisponivelException;
import com.trybe.acc.java.minhasseries.model.Episodio;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.repository.SerieRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe SerieService.*/
@Service
public class SerieService {
  @Autowired
  private SerieRepository repository;
  
  /**Método de inserir nova série.*/
  public Serie inserirSerie(Serie serie) {
    if (repository.existsByNome(serie.getNome())) {
      throw new SerieExistenteException();
    } else {
      return repository.save(serie);
    }
  }
  
  /**Método de retornar todas as séries.*/
  public List<Serie> retornarTodasSeries() {
    return repository.findAll();
  }
  
  /**Método de achar a série por id.*/
  public Serie acharSeriePorId(Integer serieId) {
    if (repository.findById(serieId).isEmpty()) {
      throw new SerieNaoEncontradaException();
    } else {
      return repository.findById(serieId).get();
    }
  }
  
  /**Método de deletar a série pelo id.*/
  public String deletaSeriePorId(Integer id) {
    if (repository.findById(id).isEmpty()) {
      throw new SerieNaoEncontradaException();
    }
    repository.deleteById(id);
    return "Série deletada com sucesso!";
  }
  
  /**Método de adicionar episódios à série.*/
  public Serie adicionarEpisodio(Integer serieId, Episodio episodio) {
  
    if (!repository.existsById(serieId)) {
      throw new SerieNaoEncontradaException();
    }
    Serie serie = repository.findById(serieId).get();
    
    if (serie.getEpisodios().stream()
        .anyMatch(ep -> ep.getNumero() == episodio.getNumero())) {
      throw new EpisodioExistenteException();
      
    } else {
      episodio.setSerie(serie);
      serie.adicionarEpisodio(episodio);
      return repository.save(serie);
    }
  }
  
  /**Método de retornar todas os episódios de uma determinada série.*/
  public List<Episodio> acharEpisodiosDaSerie(Integer serieId) {
    Serie serie = repository.findById(serieId).get();
    if (serie.getEpisodios().isEmpty() || serie.getEpisodios() == null) {
      throw new ServicoIndisponivelException();
    } else {
      return serie.getEpisodios();
    }
    
  }
  
  /**Método de retornar o total de tempo gasto em minutos.*/
  public Map<String, Integer> retornarTempoGastoTotalEmMinutos() {    
    return Map.of(
          "tempoEmMinutos", 
          repository.findAll().stream().mapToInt(serie -> serie.tempoGastoTotalEmMinutos()).sum()
          );
  }
  
}
