package br.com.ifsp.dto;

import br.com.ifsp.dominio.Matricula;
import br.com.ifsp.dominio.enums.StatusMatricula;

import java.math.BigDecimal;

public class MatriculaResponseDTO {

    private Long id;
    private AlunoResponseDTO aluno;
    private TurmaResponseDTO turma;
    private StatusMatricula status;
    private BigDecimal mediaFinal;
    private BigDecimal frequencia;

    public MatriculaResponseDTO(Matricula matricula) {
        this.id = matricula.getId();
        this.status = matricula.getStatus();
        this.mediaFinal = matricula.getMediaFinal();
        this.frequencia = matricula.getFrequencia();

        if (matricula.getAluno() != null) {
            this.aluno = new AlunoResponseDTO(matricula.getAluno());
        }
        if (matricula.getTurma() != null) {
            this.turma = new TurmaResponseDTO(matricula.getTurma());
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlunoResponseDTO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoResponseDTO aluno) {
        this.aluno = aluno;
    }

    public TurmaResponseDTO getTurma() {
        return turma;
    }

    public void setTurma(TurmaResponseDTO turma) {
        this.turma = turma;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public BigDecimal getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(BigDecimal mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public BigDecimal getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(BigDecimal frequencia) {
        this.frequencia = frequencia;
    }
}