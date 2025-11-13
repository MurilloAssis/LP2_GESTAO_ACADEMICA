package br.com.ifsp.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private BigDecimal peso = BigDecimal.ONE;

    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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