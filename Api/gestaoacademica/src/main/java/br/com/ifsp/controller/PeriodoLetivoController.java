package br.com.ifsp.controller;

import br.com.ifsp.dto.PeriodoLetivoCreateDTO;
import br.com.ifsp.dto.PeriodoLetivoResponseDTO;
import br.com.ifsp.service.PeriodoLetivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/periodos-letivos")
public class PeriodoLetivoController {

    @Autowired
    private PeriodoLetivoService periodoLetivoService;

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoLetivoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(periodoLetivoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PeriodoLetivoResponseDTO>> findAll() {
        return ResponseEntity.ok(periodoLetivoService.findAll());
    }

    @PostMapping
    public ResponseEntity<PeriodoLetivoResponseDTO> create(@Valid @RequestBody PeriodoLetivoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(periodoLetivoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoLetivoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PeriodoLetivoCreateDTO dto) {
        return ResponseEntity.ok(periodoLetivoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        periodoLetivoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}