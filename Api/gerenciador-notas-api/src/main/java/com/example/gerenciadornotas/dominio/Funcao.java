
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Funcao {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name", unique=true, nullable=false, length=30)
  private String name;
}
