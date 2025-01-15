package com.jhops10.agenda.api.mapper;

import com.jhops10.agenda.api.request.AgendaRequestDTO;
import com.jhops10.agenda.api.response.AgendaResponseDTO;
import com.jhops10.agenda.domain.entity.Agenda;
import com.jhops10.agenda.domain.entity.Paciente;
import com.jhops10.agenda.domain.service.PacienteService;
import com.jhops10.agenda.exception.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AgendaMapper {

    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;

    public AgendaMapper(ModelMapper modelMapper, PacienteService pacienteService) {
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
    }


    public Agenda toAgenda(AgendaRequestDTO requestDTO) {
        Agenda agenda = modelMapper.map(requestDTO, Agenda.class);
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(requestDTO.getPacienteId());

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente Não Encontrado!");
        }

        agenda.setPaciente(optPaciente.get());

        return agenda;
    }

    public AgendaResponseDTO toAgendaResponseDTO(Agenda agenda) {
        return modelMapper.map(agenda, AgendaResponseDTO.class);
    }

    public List<AgendaResponseDTO> toAgendaResponseDTOList(List<Agenda> agendas) {
        return agendas.stream()
                .map(agenda -> toAgendaResponseDTO(agenda))
                .toList();
    }
}
