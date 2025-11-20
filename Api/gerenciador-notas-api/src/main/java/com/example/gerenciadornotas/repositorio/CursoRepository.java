
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
  Optional<Curso> findByCode(String code);
}
