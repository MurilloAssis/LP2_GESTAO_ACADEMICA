package br.com.ifsp.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NotaCreateDTO {

    @NotNull(message = "ID da Matrícula (aluno-turma) não pode ser nulo")
    private Long matriculaId;

    @NotNull(message = "ID da Avaliação não pode ser nulo")
    private Long avaliacaoId;

    @NotNull(message = "O valor da nota não pode ser nulo")
    @DecimalMin(value = "0.0", message = "Nota não pode ser menor que 0.0")
    @DecimalMax(value = "10.0", message = "Nota não pode ser maior que 10.0")
    private BigDecimal valorNota;

    // Getters e Setters
    public Long getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Long matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }
}