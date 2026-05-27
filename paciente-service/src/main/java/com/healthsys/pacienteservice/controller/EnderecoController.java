package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.EnderecoRequestDTO;
import com.healthsys.pacienteservice.dto.EnderecoResponseDTO;
import com.healthsys.pacienteservice.service.EnderecoService;
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
@RequestMapping("/enderecos")
@Tag(name = "API Endereços", description = "Endpoints para Consultar, Criar, Atualizar e Remover Endereços")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final Counter counter;

    public EnderecoController(EnderecoService enderecoService, MeterRegistry meterRegistry) {
        this.enderecoService = enderecoService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller EnderecoController")
                .tag("controller", "EnderecoController")
                .tag("endpoint", "/enderecos")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar endereços")
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos() {
        counter.increment();
        List<EnderecoResponseDTO> enderecos = enderecoService.getEnderecos();
        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar endereços por paciente")
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecosPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        List<EnderecoResponseDTO> enderecos = enderecoService.getEnderecosPorPaciente(pacienteId);
        return ResponseEntity.ok().body(enderecos);
    }

    @PostMapping
    @Operation(summary = "Criar endereço")
    public ResponseEntity<EnderecoResponseDTO> criarEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        counter.increment();
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.criarEndereco(enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar endereço")
    public ResponseEntity<EnderecoResponseDTO> atualizarEndereco(
            @PathVariable Integer id,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        counter.increment();
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.atualizarEndereco(id, enderecoRequestDTO);
        return ResponseEntity.ok().body(enderecoResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir endereço")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        counter.increment();
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
