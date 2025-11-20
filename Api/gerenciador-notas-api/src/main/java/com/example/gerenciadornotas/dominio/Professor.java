
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@Getter @Setter @NoArgsConstructor
public class Professor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(optional=false) @JoinColumn(name="user_id", unique = true, nullable = false)
  private Usuario usuario;

  @Column(name="employee_code", unique = true, nullable = false, length = 30)
  private String employeeCode;
}
