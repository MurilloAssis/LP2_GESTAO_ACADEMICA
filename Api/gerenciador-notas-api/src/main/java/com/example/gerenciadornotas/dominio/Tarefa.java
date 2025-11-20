
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="assignments")
@Getter @Setter @NoArgsConstructor
public class Tarefa {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="course_id")
  private Curso course;

  @Column(nullable=false, length=150)
  private String name;

  @Column(nullable=false, precision=6, scale=3)
  private BigDecimal weight;
}
