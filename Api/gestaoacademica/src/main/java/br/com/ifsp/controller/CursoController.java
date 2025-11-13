package br.com.ifsp.controller;

import br.com.ifsp.dto.CursoCreateDTO;
import br.com.ifsp.dto.CursoResponseDTO;
import br.com.ifsp.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> findAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> create(@Valid @RequestBody CursoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CursoCreateDTO dto) {
        return ResponseEntity.ok(cursoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}