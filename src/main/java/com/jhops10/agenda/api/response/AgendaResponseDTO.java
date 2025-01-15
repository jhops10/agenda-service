package com.jhops10.agenda.api.response;

import java.time.LocalDateTime;

public class AgendaResponseDTO {


    private Long id;
    private String descricao;
    private LocalDateTime dataHora;
    private PacienteResponseDTO paciente;

    public AgendaResponseDTO() {
    }

    public AgendaResponseDTO(Long id, String descricao, LocalDateTime dataHora, PacienteResponseDTO paciente) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public PacienteResponseDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteResponseDTO paciente) {
        this.paciente = paciente;
    }
}
