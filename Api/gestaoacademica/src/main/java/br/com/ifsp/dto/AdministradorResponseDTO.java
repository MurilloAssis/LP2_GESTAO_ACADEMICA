package br.com.ifsp.dto;

import br.com.ifsp.dominio.Administrador;
import lombok.Data;

@Data
public class AdministradorResponseDTO {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String cargo;

    public AdministradorResponseDTO(Administrador admin) {
        this.id = admin.getId();
        this.nomeCompleto = admin.getNomeCompleto();
        this.email = admin.getUsuario().getEmail();
        this.cargo = admin.getCargo();
    }
}