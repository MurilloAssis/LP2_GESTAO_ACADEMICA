package br.com.ifsp.controller;

import br.com.ifsp.dto.MatriculaResponseDTO;
import br.com.ifsp.service.CalculoMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professor/calculos")
public class CalculoController {

    @Autowired
    private CalculoMediaService calculoMediaService;

    @PostMapping("/media/{matriculaId}")
    public ResponseEntity<MatriculaResponseDTO> calcularMediaFinal(@PathVariable Long matriculaId) {
        MatriculaResponseDTO matriculaAtualizada = calculoMediaService.calcularMediaFinal(matriculaId);
        return ResponseEntity.ok(matriculaAtualizada);
    }
}