package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.AtendimentoRequestDTO;
import com.healthsys.pacientes.dto.AtendimentoResponseDTO;
import com.healthsys.pacientes.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> getAtendimentos() {
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.getAtendimentos();
        return ResponseEntity.ok().body(atendimentos);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AtendimentoResponseDTO>> getAtendimentosPorPaciente(@PathVariable UUID pacienteId) {
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.getAtendimentosPorPaciente(pacienteId);
        return ResponseEntity.ok().body(atendimentos);
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> criarAtendimento(
            @Valid @RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoService.criarAtendimento(atendimentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> atualizarAtendimento(
            @PathVariable UUID id,
            @Valid @RequestBody AtendimentoRequestDTO atendimentoRequestDTO) {
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoService.atualizarAtendimento(id, atendimentoRequestDTO);
        return ResponseEntity.ok().body(atendimentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendimento(@PathVariable UUID id) {
        atendimentoService.deletarAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}
