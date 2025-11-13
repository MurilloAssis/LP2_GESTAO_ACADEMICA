package br.com.ifsp.dto;

import br.com.ifsp.dominio.Avaliacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AvaliacaoResponseDTO {

    private Long id;
    private Long turmaId;
    private String titulo;
    private BigDecimal peso;
    private LocalDate dataAvaliacao;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.titulo = avaliacao.getTitulo();
        this.peso = avaliacao.getPeso();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        if (avaliacao.getTurma() != null) {
            this.turmaId = avaliacao.getTurma().getId();
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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