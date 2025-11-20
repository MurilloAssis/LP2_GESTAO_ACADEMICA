
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cursos")
@Getter @Setter @NoArgsConstructor
public class Curso {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, length=120)
  private String codigo;

  @Column(nullable=false, length=200)
  private String titulo;

  @ManyToOne @JoinColumn(name="teacher_id")
  private Professor professor;
}
