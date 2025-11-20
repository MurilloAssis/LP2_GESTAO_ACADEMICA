
package com.example.gerenciadornotas.repositorio;

import com.example.gerenciadornotas.dominio.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
  List<Nota> findByEnrollmentId(Long enrollmentId);

  @Query("""
    select coalesce(sum(g.value * a.weight),0)
    from Grade g join g.assignment a
    where g.enrollment.id = :enrollmentId
  """)
  BigDecimal mediaPonderada(@Param("enrollmentId") Long enrollmentId);
}
