
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
  List<Matricula> findByStudentId(Long studentId);
  Optional<Matricula> findByStudentIdAndCourseIdAndTermId(Long s, Long c, Long t);
}
