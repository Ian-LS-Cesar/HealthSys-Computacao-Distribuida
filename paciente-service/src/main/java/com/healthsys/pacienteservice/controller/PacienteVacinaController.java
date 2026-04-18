package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.PacienteVacinaRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteVacinaResponseDTO;
import com.healthsys.pacienteservice.service.PacienteVacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paciente-vacinas")
public class PacienteVacinaController {

    private final PacienteVacinaService pacienteVacinaService;

    public PacienteVacinaController(PacienteVacinaService pacienteVacinaService) {
        this.pacienteVacinaService = pacienteVacinaService;
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<PacienteVacinaResponseDTO>> getPorPaciente(@PathVariable UUID pacienteId) {
        return ResponseEntity.ok(pacienteVacinaService.getPacienteVacinaPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<PacienteVacinaResponseDTO> vincular(
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        PacienteVacinaResponseDTO response = pacienteVacinaService.vincular(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteVacinaResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        return ResponseEntity.ok(pacienteVacinaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pacienteVacinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}