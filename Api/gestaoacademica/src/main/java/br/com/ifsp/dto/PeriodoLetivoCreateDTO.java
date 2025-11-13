package br.com.ifsp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PeriodoLetivoCreateDTO {

    @NotBlank(message = "Identificador não pode ser vazio")
    private String identificador;

    @NotNull(message = "Data de início não pode ser nula")
    private LocalDate dataInicio;

    @NotNull(message = "Data de fim não pode ser nula")
    private LocalDate dataFim;

    // Getters e Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}