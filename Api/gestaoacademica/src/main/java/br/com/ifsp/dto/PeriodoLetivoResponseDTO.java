package br.com.ifsp.dto;

import br.com.ifsp.dominio.PeriodoLetivo;
import java.time.LocalDate;

public class PeriodoLetivoResponseDTO {

    private Long id;
    private String identificador;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PeriodoLetivoResponseDTO(PeriodoLetivo periodoLetivo) {
        this.id = periodoLetivo.getId();
        this.identificador = periodoLetivo.getIdentificador();
        this.dataInicio = periodoLetivo.getDataInicio();
        this.dataFim = periodoLetivo.getDataFim();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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