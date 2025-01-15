package com.jhops10.agenda.api.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendaRequestDTO {


    @NotBlank
    private String descricao;

    @NotNull
    @Future
    private LocalDateTime dataHora;

    @NotNull
    private Long pacienteId;

    public AgendaRequestDTO() {
    }

    public AgendaRequestDTO(String descricao, LocalDateTime dataHora, Long pacienteId) {
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.pacienteId = pacienteId;
    }

    public @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank String descricao) {
        this.descricao = descricao;
    }

    public @NotNull @Future LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(@NotNull @Future LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public @NotNull Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(@NotNull Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
