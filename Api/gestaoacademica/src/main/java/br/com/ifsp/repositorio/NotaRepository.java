package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    Optional<Nota> findByMatriculaIdAndAvaliacaoId(Long matriculaId, Long avaliacaoId);

    List<Nota> findAllByMatriculaId(Long matriculaId);

    List<Nota> findAllByAvaliacaoId(Long avaliacaoId);
}