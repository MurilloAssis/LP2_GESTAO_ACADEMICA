
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="grades")
@Getter @Setter @NoArgsConstructor
public class Nota {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="enrollment_id")
  private Matricula matricula;

  @ManyToOne(optional=false) @JoinColumn(name="assignment_id")
  private Tarefa tarefa;

  @Column(nullable=false, precision=5, scale=2)
  private BigDecimal value;
}
