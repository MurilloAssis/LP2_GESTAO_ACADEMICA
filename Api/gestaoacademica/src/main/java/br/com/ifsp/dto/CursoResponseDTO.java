package br.com.ifsp.dto;

import br.com.ifsp.dominio.Curso;
import lombok.Data;

public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String codigoCurso;

    public CursoResponseDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.codigoCurso = curso.getCodigoCurso();
    }
}