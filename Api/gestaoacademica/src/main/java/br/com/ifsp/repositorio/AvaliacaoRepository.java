package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Optional<Avaliacao> findByTurmaIdAndTitulo(Long turmaId, String titulo);

    List<Avaliacao> findAllByTurmaId(Long turmaId);
}