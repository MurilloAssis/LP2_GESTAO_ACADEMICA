package br.com.ifsp.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matricula_id")
    private Matricula matricula; // Vínculo com o aluno na turma

    @Column(name = "valor_nota", nullable = false)
    private BigDecimal valorNota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }
}