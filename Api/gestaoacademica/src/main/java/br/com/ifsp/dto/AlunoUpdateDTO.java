package br.com.ifsp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoUpdateDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nomeCompleto;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

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
}