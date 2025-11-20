
package com.example.gerenciadornotas.web.dto;

import com.example.gerenciadornotas.dominio.Nota;
import java.math.BigDecimal;

public record NotaDTO(Long id, Long matriculaId, Long tarefaId, BigDecimal value) {
  public static NotaDTO from(Nota g) {
    return new NotaDTO(g.getId(), g.getMatricula().getId(), g.getTarefa().getId(), g.getValue());
  }
}
