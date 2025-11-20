
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
  List<Tarefa> findByCourseId(Long courseId);
}
