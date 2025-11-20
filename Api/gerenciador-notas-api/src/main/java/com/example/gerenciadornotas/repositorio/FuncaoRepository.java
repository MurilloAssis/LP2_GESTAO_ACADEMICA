
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
  Optional<Funcao> findByName(String name);
}
