
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="enrollments")
@Getter @Setter @NoArgsConstructor
public class Matricula {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="student_id")
  private Estudante estudante;

  @ManyToOne(optional=false) @JoinColumn(name="course_id")
  private Curso curso;

  @ManyToOne(optional=false) @JoinColumn(name="term_id")
  private Periodo periodo;
}
