package com.jhops10.agenda.api.request;

public record PacienteRequestDTO(String nome,
                                 String sobrenome,
                                 String cpf,
                                 String email) {
}
