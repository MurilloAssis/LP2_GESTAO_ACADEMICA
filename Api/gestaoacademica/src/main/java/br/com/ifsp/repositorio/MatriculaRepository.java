package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Validação para impedir matrícula duplicada (mesmo aluno na mesma turma)
    Optional<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}