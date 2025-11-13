package br.com.ifsp.dto;

import br.com.ifsp.dominio.Professor;
import lombok.Data;

@Data
public class ProfessorResponseDTO {

    private Long id;
    private String nomeCompleto;
    private String matricula;
    private String email;

    public ProfessorResponseDTO(Professor professor) {
        this.id = professor.getId();
        this.nomeCompleto = professor.getNomeCompleto();
        this.matricula = professor.getMatricula();
        this.email = professor.getUsuario().getEmail();
    }
}