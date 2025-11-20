
package com.example.gerenciadornotas.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class Usuario {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="email", unique = true, nullable = false, length = 150)
  private String email;

  @Column(name="password_hash", nullable = false, length = 120)
  private String senhaHash;

  @Column(name="full_name", nullable = false, length = 120)
  private String nomeCompleto;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Funcao> roles = new HashSet<>();
}
