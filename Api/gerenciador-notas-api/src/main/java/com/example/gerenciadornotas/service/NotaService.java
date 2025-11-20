
package com.example.gerenciadornotas.service;

import com.example.gerenciadornotas.dominio.Tarefa;
import com.example.gerenciadornotas.dominio.Matricula;
import com.example.gerenciadornotas.dominio.Nota;
import com.example.gerenciadornotas.repositorio.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class NotaService {

  private final NotaRepository notaRepositorio;

  @Transactional
  public Nota upsert(Long matriculaId, Long tarefaId, BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal("10.00")) > 0) {
      throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
    }
    Nota g = notaRepositorio.findAll().stream()
      .filter(x -> x.getMatricula().getId().equals(matriculaId) && x.getTarefa().getId().equals(tarefaId))
      .findFirst().orElseGet(() -> {
        Nota ng = new Nota();
        Matricula e = new Matricula(); e.setId(matriculaId); ng.setMatricula(e);
        Tarefa a = new Tarefa(); a.setId(tarefaId); ng.setTarefa(a);
        return ng;
      });
    g.setValue(value);
    return notaRepositorio.save(g);
  }

  @Transactional(readOnly = true)
  public BigDecimal mediaPonderada(Long matriculaId) {
    var avg = notaRepositorio.mediaPonderada(matriculaId);
    return avg == null ? BigDecimal.ZERO : avg.setScale(2, RoundingMode.HALF_UP);
  }
}
