
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
  Optional<Estudante> findByRegistrationCode(String registrationCode);
}
