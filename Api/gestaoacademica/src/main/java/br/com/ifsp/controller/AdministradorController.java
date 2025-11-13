package br.com.ifsp.controller;

import br.com.ifsp.dto.AdministradorCreateDTO;
import br.com.ifsp.dto.AdministradorResponseDTO;
import br.com.ifsp.dto.AdministradorUpdateDTO;
import br.com.ifsp.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> findById(@PathVariable Long id) {
        AdministradorResponseDTO responseDTO = adminService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdministradorResponseDTO>> findAll() {
        List<AdministradorResponseDTO> lista = adminService.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AdministradorResponseDTO> create(@Valid @RequestBody AdministradorCreateDTO dto) {
        AdministradorResponseDTO responseDTO = adminService.createAdministrador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AdministradorUpdateDTO dto) {
        AdministradorResponseDTO responseDTO = adminService.updateAdministrador(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adminService.deleteAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}