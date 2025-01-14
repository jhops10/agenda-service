package com.jhops10.agenda.api.controller;

import com.jhops10.agenda.api.mapper.PacienteMapper;
import com.jhops10.agenda.api.request.PacienteRequestDTO;
import com.jhops10.agenda.api.response.PacienteResponseDTO;
import com.jhops10.agenda.domain.entity.Paciente;
import com.jhops10.agenda.domain.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    public PacienteController(PacienteService pacienteService, PacienteMapper pacienteMapper) {
        this.pacienteService = pacienteService;
        this.pacienteMapper = pacienteMapper;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> salvar(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequestDTO);
        Paciente pacienteSalvo = pacienteService.salvar(paciente);
        PacienteResponseDTO pacienteResponseDTO = pacienteMapper.toPacienteResponseDTO(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        List<PacienteResponseDTO> pacientesDTO = pacienteMapper.toPacienteResponseDTOList(pacientes);
        return ResponseEntity.ok().body(pacientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PacienteResponseDTO pacienteResponseDTO = pacienteMapper.toPacienteResponseDTO(optPaciente.get());
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@PathVariable("id") Long id, @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequestDTO);
        Paciente pacienteAtualizado = pacienteService.alterar(id, paciente);
        PacienteResponseDTO pacienteResponseDTO = pacienteMapper.toPacienteResponseDTO(pacienteAtualizado);
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        pacienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
