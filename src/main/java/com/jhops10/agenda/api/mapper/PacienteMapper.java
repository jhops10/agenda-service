package com.jhops10.agenda.api.mapper;

import com.jhops10.agenda.api.request.PacienteRequestDTO;
import com.jhops10.agenda.api.response.PacienteResponseDTO;
import com.jhops10.agenda.domain.entity.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteMapper {

    private final ModelMapper modelMapper;

    public PacienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Paciente toPaciente(PacienteRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Paciente.class);
    }

    public PacienteResponseDTO toPacienteResponseDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteResponseDTO.class);
    }

    public List<PacienteResponseDTO> toPacienteResponseDTOList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(paciente -> toPacienteResponseDTO(paciente))
                .toList();
    }


}
