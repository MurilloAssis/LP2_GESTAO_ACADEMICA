
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
  Optional<Periodo> findByName(String name);
}
