package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.PacienteVacinaRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteVacinaResponseDTO;
import com.healthsys.pacienteservice.service.PacienteVacinaService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/paciente-vacinas")
@Tag(name = "API Vínculos Paciente-Vacina", description = "Endpoints para Consultar, Criar, Atualizar e Remover Vínculos Entre Pacientes e Vacinas")
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
    @Operation(summary = "Listar vínculos por paciente")
    public ResponseEntity<List<PacienteVacinaResponseDTO>> getPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        return ResponseEntity.ok(pacienteVacinaService.getPacienteVacinaPorPaciente(pacienteId));
    }

    @PostMapping
    @Operation(summary = "Vincular vacina ao paciente")
    public ResponseEntity<PacienteVacinaResponseDTO> vincular(
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        counter.increment();
        PacienteVacinaResponseDTO response = pacienteVacinaService.vincular(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo paciente-vacina")
    public ResponseEntity<PacienteVacinaResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PacienteVacinaRequestDTO requestDTO) {
        counter.increment();
        return ResponseEntity.ok(pacienteVacinaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir vínculo paciente-vacina")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        counter.increment();
        pacienteVacinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}