package br.com.ifsp.controller;

import br.com.ifsp.dto.TurmaCreateDTO;
import br.com.ifsp.dto.TurmaResponseDTO;
import br.com.ifsp.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> findAll() {
        return ResponseEntity.ok(turmaService.findAll());
    }

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> create(@Valid @RequestBody TurmaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TurmaCreateDTO dto) {
        return ResponseEntity.ok(turmaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}