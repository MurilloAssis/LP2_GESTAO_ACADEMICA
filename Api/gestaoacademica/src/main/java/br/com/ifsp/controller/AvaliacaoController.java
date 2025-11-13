package br.com.ifsp.controller;

import br.com.ifsp.dto.AvaliacaoCreateDTO;
import br.com.ifsp.dto.AvaliacaoResponseDTO;
import br.com.ifsp.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.findById(id));
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> findAllByTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(avaliacaoService.findAllByTurma(turmaId));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> create(@Valid @RequestBody AvaliacaoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AvaliacaoCreateDTO dto) {
        return ResponseEntity.ok(avaliacaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}