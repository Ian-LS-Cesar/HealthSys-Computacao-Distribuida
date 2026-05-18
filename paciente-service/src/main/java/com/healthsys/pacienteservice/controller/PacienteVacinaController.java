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
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/paciente-vacinas")
public class PacienteVacinaController {

    private final PacienteVacinaService pacienteVacinaService;
    private final Counter counter;

    public PacienteVacinaController(PacienteVacinaService pacienteVacinaService, MeterRegistry meterRegistry) {
        this.pacienteVacinaService = pacienteVacinaService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller PacienteVacinaController")
                .tag("controller", "PacienteVacinaController")
                .tag("endpoint", "/paciente-vacinas")
                .register(meterRegistry);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<PacienteVacinaResponseDTO>> getPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        return ResponseEntity.ok(pacienteVacinaService.getPacienteVacinaPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<PacienteVacinaResponseDTO> vincular(
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        counter.increment();
        PacienteVacinaResponseDTO response = pacienteVacinaService.vincular(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteVacinaResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        counter.increment();
        return ResponseEntity.ok(pacienteVacinaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        counter.increment();
        pacienteVacinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}