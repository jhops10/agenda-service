package com.jhops10.agenda.api.mapper;

import com.jhops10.agenda.api.request.PacienteRequestDTO;
import com.jhops10.agenda.api.response.PacienteResponseDTO;
import com.jhops10.agenda.domain.entity.Paciente;

public class PacienteMapper {

    public static Paciente toPaciente(PacienteRequestDTO requestDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(requestDTO.nome());
        paciente.setSobrenome(requestDTO.sobrenome());
        paciente.setCpf(requestDTO.cpf());
        paciente.setEmail(requestDTO.email());
        return paciente;
    }

    public static PacienteResponseDTO toPacienteResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                paciente.getEmail());
    }
}
