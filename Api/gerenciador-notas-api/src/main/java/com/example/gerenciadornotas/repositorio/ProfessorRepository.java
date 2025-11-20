
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
  Optional<Professor> findByEmployeeCode(String employeeCode);
}
