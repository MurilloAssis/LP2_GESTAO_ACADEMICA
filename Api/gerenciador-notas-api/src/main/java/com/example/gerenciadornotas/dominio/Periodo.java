
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="terms")
@Getter @Setter @NoArgsConstructor
public class Periodo {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false, length=40) private String name;
  private LocalDate startDate;
  private LocalDate endDate;
}
