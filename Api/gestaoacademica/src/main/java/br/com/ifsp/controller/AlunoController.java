package br.com.ifsp.controller;

import br.com.ifsp.dto.AlunoCreateDTO;
import br.com.ifsp.dto.AlunoResponseDTO;
import br.com.ifsp.dto.AlunoUpdateDTO;
import br.com.ifsp.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> findById(@PathVariable Long id) {
        AlunoResponseDTO responseDTO = alunoService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> findAll() {
        List<AlunoResponseDTO> lista = alunoService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> create(@Valid @RequestBody AlunoCreateDTO dto) {
        AlunoResponseDTO responseDTO = alunoService.createAluno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AlunoUpdateDTO dto) {
        AlunoResponseDTO responseDTO = alunoService.updateAluno(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }
}