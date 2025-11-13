package br.com.ifsp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TurmaCreateDTO {

    @NotNull(message = "ID da Disciplina não pode ser nulo")
    private Long disciplinaId;

    @NotNull(message = "ID do Período Letivo não pode ser nulo")
    private Long periodoLetivoId;

    @NotNull(message = "ID do Professor não pode ser nulo")
    private Long professorId;

    @NotBlank(message = "Código da Turma não pode ser vazio")
    private String codigoTurma;

    // Getters e Setters
    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getPeriodoLetivoId() {
        return periodoLetivoId;
    }

    public void setPeriodoLetivoId(Long periodoLetivoId) {
        this.periodoLetivoId = periodoLetivoId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }
}