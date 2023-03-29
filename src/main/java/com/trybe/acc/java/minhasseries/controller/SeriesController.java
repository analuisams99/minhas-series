package com.trybe.acc.java.minhasseries.controller;

import com.trybe.acc.java.minhasseries.model.Episodio;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.service.SerieService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**Classe SeriesController.*/
@RestController
@RequestMapping("/series")
public class SeriesController {
  
  @Autowired
  private SerieService serieService;
  
  @PostMapping
  public ResponseEntity<Serie> inserirSerie(@RequestBody Serie serie) {
    return ResponseEntity.ok().body(serieService.inserirSerie(serie));
  }
  
  @GetMapping
  public ResponseEntity<List<Serie>> retornarTodasSeries() {
    return ResponseEntity.ok().body(serieService.retornarTodasSeries());
  }
  
  @DeleteMapping("/{serieId}")
  public ResponseEntity<String> deletaSeriePorId(@PathVariable Integer serieId) {
    return ResponseEntity.ok().body(serieService.deletaSeriePorId(serieId));
  }
  
  @PostMapping("/{serieId}/episodios")
  @CircuitBreaker(name = "episodios")
  public ResponseEntity<Serie> adicionarEpisodio(
      @PathVariable Integer serieId, 
      @RequestBody Episodio episodio) {
    return ResponseEntity.ok().body(serieService.adicionarEpisodio(serieId, episodio));
  }
  
  @GetMapping("/{serieId}/episodios")
  public ResponseEntity<List<Episodio>> acharEpisodiosDaSerie(@PathVariable Integer serieId) {
    return ResponseEntity.ok().body(serieService.acharEpisodiosDaSerie(serieId));
  }

  @GetMapping("/tempo")
  public ResponseEntity<Map<String, Integer>> retornarTempoGastoTotalEmMinutos() {
    return ResponseEntity.ok().body(serieService.retornarTempoGastoTotalEmMinutos());
  }

}
