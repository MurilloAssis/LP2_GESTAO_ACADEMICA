package br.com.ifsp.controller;

import br.com.ifsp.dto.DisciplinaCreateDTO;
import br.com.ifsp.dto.DisciplinaResponseDTO;
import br.com.ifsp.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> findAll() {
        return ResponseEntity.ok(disciplinaService.findAll());
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> create(@Valid @RequestBody DisciplinaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DisciplinaCreateDTO dto) {
        return ResponseEntity.ok(disciplinaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}