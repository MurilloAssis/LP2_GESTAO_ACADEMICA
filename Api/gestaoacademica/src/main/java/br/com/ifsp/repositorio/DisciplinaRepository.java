package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    Optional<Disciplina> findByCodigoDisciplina(String codigoDisciplina);
}