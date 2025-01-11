package com.jhops10.agenda.api.response;

public record PacienteResponseDTO(Long id,
                                  String nome,
                                  String sobrenome,
                                  String cpf,
                                  String email) {
}
