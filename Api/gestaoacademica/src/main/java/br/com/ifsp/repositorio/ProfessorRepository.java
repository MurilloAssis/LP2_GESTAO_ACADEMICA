package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByMatricula(String matricula);
}