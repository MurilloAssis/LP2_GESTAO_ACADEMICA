package br.com.ifsp.dto;


import br.com.ifsp.dominio.Aluno;
import lombok.Data;

@Data
public class AlunoResponseDTO {

    private Long id;
    private String nomeCompleto;
    private String matricula;
    private String email;

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nomeCompleto = aluno.getNomeCompleto();
        this.matricula = aluno.getMatricula();
        this.email = aluno.getUsuario().getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}