package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.ComorbidadeRequestDTO;
import com.healthsys.pacienteservice.dto.ComorbidadeResponseDTO;
import com.healthsys.pacienteservice.service.ComorbidadeService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comorbidades")
@Tag(name = "API Comorbidades", description = "Endpoints para Consultar, Criar, Atualizar e Remover Comorbidades")
public class ComorbidadeController {
    private final ComorbidadeService comorbidadeService;
    private final Counter counter;

    public ComorbidadeController(ComorbidadeService comorbidadeService, MeterRegistry meterRegistry) {
        this.comorbidadeService = comorbidadeService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller ComorbidadeController")
                .tag("controller", "ComorbidadeController")
                .tag("endpoint", "/comorbidades")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar comorbidades")
    public ResponseEntity<List<ComorbidadeResponseDTO>> getComorbidades() {
        counter.increment();
        return ResponseEntity.ok(comorbidadeService.getComorbidades());
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar comorbidades por paciente")
    public ResponseEntity<List<ComorbidadeResponseDTO>> getComorbidadesPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        return ResponseEntity.ok(comorbidadeService.getComorbidadesPorPaciente(pacienteId));
    }

    @PostMapping
    @Operation(summary = "Criar comorbidade")
    public ResponseEntity<ComorbidadeResponseDTO> criarComorbidade(@Valid @RequestBody ComorbidadeRequestDTO comorbidadeRequestDTO) {
        counter.increment();
        return ResponseEntity.status(HttpStatus.CREATED).body(comorbidadeService.criarComorbidade(comorbidadeRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar comorbidade")
    public ResponseEntity<ComorbidadeResponseDTO> atualizarComorbidade(
            @PathVariable Integer id,
            @Valid @RequestBody ComorbidadeRequestDTO comorbidadeRequestDTO) {
        counter.increment();
        return ResponseEntity.ok(comorbidadeService.atualizarComorbidade(id, comorbidadeRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir comorbidade")
    public ResponseEntity<Void> deletarComorbidade(@PathVariable Integer id) {
        counter.increment();
        comorbidadeService.deletarComorbidade(id);
        return ResponseEntity.noContent().build();
    }
}

