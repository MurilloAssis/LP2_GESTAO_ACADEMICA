package br.com.ifsp.dto;

import br.com.ifsp.dominio.Nota;
import java.math.BigDecimal;

public class NotaResponseDTO {

    private Long id;
    private Long matriculaId;
    private String alunoNome;
    private Long avaliacaoId;
    private String avaliacaoTitulo;
    private BigDecimal valorNota;

    public NotaResponseDTO(Nota nota) {
        this.id = nota.getId();
        this.valorNota = nota.getValorNota();

        if (nota.getMatricula() != null) {
            this.matriculaId = nota.getMatricula().getId();
            if (nota.getMatricula().getAluno() != null) {
                this.alunoNome = nota.getMatricula().getAluno().getNomeCompleto();
            }
        }
        if (nota.getAvaliacao() != null) {
            this.avaliacaoId = nota.getAvaliacao().getId();
            this.avaliacaoTitulo = nota.getAvaliacao().getTitulo();
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public String getAvaliacaoTitulo() {
        return avaliacaoTitulo;
    }

    public void setAvaliacaoTitulo(String avaliacaoTitulo) {
        this.avaliacaoTitulo = avaliacaoTitulo;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }
}