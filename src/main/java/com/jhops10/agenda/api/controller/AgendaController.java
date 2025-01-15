package com.jhops10.agenda.api.controller;

import com.jhops10.agenda.api.mapper.AgendaMapper;
import com.jhops10.agenda.api.request.AgendaRequestDTO;
import com.jhops10.agenda.api.response.AgendaResponseDTO;
import com.jhops10.agenda.domain.entity.Agenda;
import com.jhops10.agenda.domain.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;

    public AgendaController(AgendaService agendaService, AgendaMapper agendaMapper) {
        this.agendaService = agendaService;
        this.agendaMapper = agendaMapper;
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> buscarTodos() {
        List<Agenda> agendas = agendaService.listarTodos();
        List<AgendaResponseDTO> agendaResponseDTOList = agendaMapper.toAgendaResponseDTOList(agendas);
        return ResponseEntity.ok().body(agendaResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Agenda> optAgenda = agendaService.buscarPorId(id);

        if (optAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponseDTO agendaResponseDTO = agendaMapper.toAgendaResponseDTO(optAgenda.get());
        return ResponseEntity.ok().body(agendaResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AgendaResponseDTO> salvar(@Valid @RequestBody AgendaRequestDTO agendaRequestDTO) {
        Agenda agenda = agendaMapper.toAgenda(agendaRequestDTO);
        Agenda agendaSalvo = agendaService.salvar(agenda);
        AgendaResponseDTO agendaResponseDTO = agendaMapper.toAgendaResponseDTO(agendaSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponseDTO);
    }

}
