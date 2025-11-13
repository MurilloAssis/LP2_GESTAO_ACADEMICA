package br.com.ifsp.repositorio;

import br.com.ifsp.dominio.PeriodoLetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodoLetivoRepository extends JpaRepository<PeriodoLetivo, Long> {
    Optional<PeriodoLetivo> findByIdentificador(String identificador);
}