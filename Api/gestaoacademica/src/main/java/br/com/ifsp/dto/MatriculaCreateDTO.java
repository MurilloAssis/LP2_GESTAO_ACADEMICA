package br.com.ifsp.dto;

import jakarta.validation.constraints.NotNull;

public class MatriculaCreateDTO {

    @NotNull(message = "ID do Aluno não pode ser nulo")
    private Long alunoId;

    @NotNull(message = "ID da Turma não pode ser nulo")
    private Long turmaId;

    // Getters e Setters
    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}