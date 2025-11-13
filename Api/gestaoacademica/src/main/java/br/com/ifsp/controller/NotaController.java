package br.com.ifsp.controller;

import br.com.ifsp.dto.NotaCreateDTO;
import br.com.ifsp.dto.NotaResponseDTO;
import br.com.ifsp.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping("/avaliacao/{avaliacaoId}")
    public ResponseEntity<List<NotaResponseDTO>> findByAvaliacao(@PathVariable Long avaliacaoId) {
        return ResponseEntity.ok(notaService.findByAvaliacao(avaliacaoId));
    }

    @GetMapping("/matricula/{matriculaId}")
    public ResponseEntity<List<NotaResponseDTO>> findByMatricula(@PathVariable Long matriculaId) {
        return ResponseEntity.ok(notaService.findByMatricula(matriculaId));
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> create(@Valid @RequestBody NotaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody NotaCreateDTO dto) {
        return ResponseEntity.ok(notaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}