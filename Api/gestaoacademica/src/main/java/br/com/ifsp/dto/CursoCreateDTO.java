package br.com.ifsp.dto;

import jakarta.validation.constraints.NotBlank;

public class CursoCreateDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Código do curso não pode ser vazio")
    private String codigoCurso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}