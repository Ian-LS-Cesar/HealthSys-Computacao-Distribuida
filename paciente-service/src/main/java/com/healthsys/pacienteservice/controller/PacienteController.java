package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.PacienteRequestDTO;
import com.healthsys.pacienteservice.dto.PacienteResponseDTO;
import com.healthsys.pacienteservice.dto.validators.CreatePacienteValidationGroup;
import com.healthsys.pacienteservice.service.PacienteService;
import jakarta.validation.groups.Default;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "API Pacientes", description = "Endpoints para Consultar, Criar, Atualizar e Remover Pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    private final Counter counter;

    public PacienteController(PacienteService pacienteService, MeterRegistry meterRegistry) {
        this.pacienteService = pacienteService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller PacienteController")
                .tag("controller", "PacienteController")
                .tag("endpoint", "/pacientes")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        counter.increment();
        List<PacienteResponseDTO> pacientes = pacienteService.getPacientes();
        return ResponseEntity.ok().body(pacientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID")
    public ResponseEntity<PacienteResponseDTO> getPacienteById(@PathVariable UUID id) {
        counter.increment();
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar paciente por CPF")
    public ResponseEntity<PacienteResponseDTO> getPacienteByCpf(@PathVariable String cpf) {
        counter.increment();
        return ResponseEntity.ok(pacienteService.getPacienteByCpf(cpf));
    }

    @PostMapping
    @Operation(summary = "Criar paciente")
    public ResponseEntity<PacienteResponseDTO> criarPaciente(@Validated({Default.class, CreatePacienteValidationGroup.class}) @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        counter.increment();
        PacienteResponseDTO pacienteResponseDTO = pacienteService.criarPaciente(pacienteRequestDTO);
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }

    @PutMapping({"/{id}"})
    @Operation(summary = "Atualizar paciente")
    public ResponseEntity<PacienteResponseDTO> updateUsuario(@PathVariable UUID id,@Validated({Default.class, CreatePacienteValidationGroup.class}) @RequestBody PacienteRequestDTO usuarioRequestDTO){
        counter.increment();
        PacienteResponseDTO pacienteResponseDTO = pacienteService.atualizarPaciente(id, usuarioRequestDTO);
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir paciente")
    public ResponseEntity<PacienteResponseDTO> deletarPaciente(@PathVariable UUID id) {
        counter.increment();
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
