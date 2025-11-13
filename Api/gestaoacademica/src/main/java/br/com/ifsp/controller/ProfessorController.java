package br.com.ifsp.controller;

import br.com.ifsp.dto.ProfessorCreateDTO;
import br.com.ifsp.dto.ProfessorResponseDTO;
import br.com.ifsp.dto.ProfessorUpdateDTO;
import br.com.ifsp.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> findById(@PathVariable Long id) {
        ProfessorResponseDTO responseDTO = professorService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> findAll() {
        List<ProfessorResponseDTO> lista = professorService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> create(@Valid @RequestBody ProfessorCreateDTO dto) {
        ProfessorResponseDTO responseDTO = professorService.createProfessor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProfessorUpdateDTO dto) {
        ProfessorResponseDTO responseDTO = professorService.updateProfessor(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}