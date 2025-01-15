package com.jhops10.agenda.api.mapper;

import com.jhops10.agenda.api.request.AgendaRequestDTO;
import com.jhops10.agenda.api.response.AgendaResponseDTO;
import com.jhops10.agenda.domain.entity.Agenda;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgendaMapper {

    private final ModelMapper modelMapper;

    public AgendaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Agenda toAgenda(AgendaRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Agenda.class);
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
