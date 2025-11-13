package br.com.ifsp.controller;

import br.com.ifsp.dto.MatriculaCreateDTO;
import br.com.ifsp.dto.MatriculaResponseDTO;
import br.com.ifsp.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> findAll() {
        return ResponseEntity.ok(matriculaService.findAll());
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> create(@Valid @RequestBody MatriculaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}