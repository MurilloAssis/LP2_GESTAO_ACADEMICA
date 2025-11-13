package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    // Validação para a chave única (disciplina, período, código)
    Optional<Turma> findByDisciplinaIdAndPeriodoLetivoIdAndCodigoTurma(
            Long disciplinaId, Long periodoLetivoId, String codigoTurma);
}