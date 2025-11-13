package br.com.ifsp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorUpdateDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nomeCompleto;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @NotBlank(message = "Matrícula não pode ser vazia")
    private String matricula;

    private String departamento;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}