package com.jhops10.agenda.domain.service;

import com.jhops10.agenda.domain.entity.Paciente;
import com.jhops10.agenda.domain.repository.PacienteRepository;
import com.jhops10.agenda.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente salvar(Paciente paciente) {
        boolean existeCpf = false;
        Optional<Paciente> optPaciente = pacienteRepository.findByCpf(paciente.getCpf());

        if (optPaciente.isPresent()) {
            if (!optPaciente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new BusinessException("CPF Já Cadastrado!");
        }

        return pacienteRepository.save(paciente);
    }


    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }


}
