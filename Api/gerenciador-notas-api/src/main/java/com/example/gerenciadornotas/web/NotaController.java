
package com.example.gerenciadornotas.web;

import com.example.gerenciadornotas.service.NotaService;
import com.example.gerenciadornotas.web.dto.NotaDTO;
import com.example.gerenciadornotas.web.dto.NotaInsertUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {

  private final NotaService notaService;

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
  public ResponseEntity<NotaDTO> upsert(@RequestBody NotaInsertUpdateDTO dto) {
    var saved = notaService.upsert(dto.matriculaId(), dto.tarefaId(), dto.value());
    return ResponseEntity.ok(NotaDTO.from(saved));
  }

  @GetMapping("/media/{enrollmentId}")
  public ResponseEntity<BigDecimal> avg(@PathVariable Long matriculaId) {
    return ResponseEntity.ok(notaService.mediaPonderada(matriculaId));
  }
}
