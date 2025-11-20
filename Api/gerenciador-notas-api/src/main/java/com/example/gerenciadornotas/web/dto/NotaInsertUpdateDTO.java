
package com.example.gerenciadornotas.web.dto;

import java.math.BigDecimal;
public record NotaInsertUpdateDTO(Long matriculaId, Long tarefaId, BigDecimal value) {}
