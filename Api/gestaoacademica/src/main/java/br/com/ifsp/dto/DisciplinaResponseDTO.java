package br.com.ifsp.dto;

import br.com.ifsp.dominio.Disciplina;

public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigoDisciplina;
    private Integer cargaHoraria;
    private CursoResponseDTO curso; // Resposta aninhada

    public DisciplinaResponseDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.codigoDisciplina = disciplina.getCodigoDisciplina();
        this.cargaHoraria = disciplina.getCargaHoraria();
        if (disciplina.getCurso() != null) {
            this.curso = new CursoResponseDTO(disciplina.getCurso());
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public CursoResponseDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoResponseDTO curso) {
        this.curso = curso;
    }
}