package br.com.ifsp.dto;

import br.com.ifsp.dominio.Turma;

public class TurmaResponseDTO {

    private Long id;
    private String codigoTurma;
    private DisciplinaResponseDTO disciplina;
    private PeriodoLetivoResponseDTO periodoLetivo;
    private ProfessorResponseDTO professor;

    public TurmaResponseDTO(Turma turma) {
        this.id = turma.getId();
        this.codigoTurma = turma.getCodigoTurma();

        if (turma.getDisciplina() != null) {
            this.disciplina = new DisciplinaResponseDTO(turma.getDisciplina());
        }
        if (turma.getPeriodoLetivo() != null) {
            this.periodoLetivo = new PeriodoLetivoResponseDTO(turma.getPeriodoLetivo());
        }
        if (turma.getProfessor() != null) {
            this.professor = new ProfessorResponseDTO(turma.getProfessor());
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public DisciplinaResponseDTO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaResponseDTO disciplina) {
        this.disciplina = disciplina;
    }

    public PeriodoLetivoResponseDTO getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(PeriodoLetivoResponseDTO periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public ProfessorResponseDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorResponseDTO professor) {
        this.professor = professor;
    }
}