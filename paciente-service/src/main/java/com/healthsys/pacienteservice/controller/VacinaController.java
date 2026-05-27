package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.service.VacinaService;
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
@RequestMapping("/vacinas")
@Tag(name = "API Vacinas", description = "Endpoints para Consultar, Criar, Atualizar e Remover Vacinas")
public class VacinaController {

    private final VacinaService vacinaService;
    private final Counter counter;

    public VacinaController(VacinaService vacinaService, MeterRegistry meterRegistry) {
        this.vacinaService = vacinaService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller VacinaController")
                .tag("controller", "VacinaController")
                .tag("endpoint", "/vacinas")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar vacinas")
    public ResponseEntity<List<VacinaResponseDTO>> getVacinas() {
        counter.increment();
        return ResponseEntity.ok(vacinaService.getVacinas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vacina por ID")
    public ResponseEntity<VacinaResponseDTO> getVacinaById(@PathVariable UUID id) {
        counter.increment();
        return ResponseEntity.ok(vacinaService.getVacinaById(id));
    }

    @PostMapping
    @Operation(summary = "Criar vacina")
    public ResponseEntity<VacinaResponseDTO> criarVacina(
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        counter.increment();
        VacinaResponseDTO vacina = vacinaService.criarVacina(vacinaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacina);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vacina")
    public ResponseEntity<VacinaResponseDTO> atualizarVacina(
            @PathVariable UUID id,
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        counter.increment();
        return ResponseEntity.ok(vacinaService.atualizarVacina(id, vacinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir vacina")
    public ResponseEntity<Void> deletarVacina(@PathVariable UUID id) {
        counter.increment();
        vacinaService.deletarVacina(id);
        return ResponseEntity.noContent().build();
    }
}