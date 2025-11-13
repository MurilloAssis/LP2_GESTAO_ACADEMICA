package br.com.ifsp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AvaliacaoCreateDTO {

    @NotNull(message = "ID da Turma não pode ser nulo")
    private Long turmaId;

    @NotBlank(message = "Título não pode ser vazio")
    private String titulo;

    @NotNull(message = "Peso não pode ser nulo")
    @DecimalMin(value = "0.01", message = "Peso deve ser maior que zero")
    private BigDecimal peso;

    private LocalDate dataAvaliacao;

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}