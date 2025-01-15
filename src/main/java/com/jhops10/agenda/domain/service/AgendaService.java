package com.jhops10.agenda.domain.service;

import com.jhops10.agenda.domain.entity.Agenda;
import com.jhops10.agenda.domain.entity.Paciente;
import com.jhops10.agenda.domain.repository.AgendaRepository;
import com.jhops10.agenda.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final PacienteService pacienteService;

    public AgendaService(AgendaRepository agendaRepository, PacienteService pacienteService) {
        this.agendaRepository = agendaRepository;
        this.pacienteService = pacienteService;
    }

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> optHorario = agendaRepository.findByDataHora(agenda.getDataHora());

        if (optHorario.isPresent()) {
            throw new BusinessException("Já existe um agendamento para esse horário");
        }

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());


        return agendaRepository.save(agenda);
    }


}
