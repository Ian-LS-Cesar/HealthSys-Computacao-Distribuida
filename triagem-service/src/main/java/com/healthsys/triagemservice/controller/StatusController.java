package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.StatusRequestDTO;
import com.healthsys.triagemservice.dto.StatusResponseDTO;
import com.healthsys.triagemservice.service.StatusService;
import jakarta.validation.Valid;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
@Tag(name = "API Status", description = "Endpoints para Consultar, Criar, Atualizar e Remover Status")
public class StatusController {
    private final StatusService statusService;
    private final Counter counter;

    public StatusController(StatusService statusService, MeterRegistry meterRegistry) {
        this.statusService = statusService;
        this.counter = Counter.builder("triagem_service_requests_total")
                .description("Total de chamadas ao controller StatusController")
                .tag("controller", "StatusController")
                .tag("endpoint", "/status")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar status")
    public ResponseEntity<List<StatusResponseDTO>> getStatus(){
        counter.increment();
        List<StatusResponseDTO> status = statusService.getStatus();
        return ResponseEntity.ok().body(status);
    }

    @PostMapping
    @Operation(summary = "Criar status")
    public ResponseEntity<StatusResponseDTO> criarStatus(@Valid @RequestBody StatusRequestDTO statusRequestDTO){
        counter.increment();
        StatusResponseDTO status = statusService.criarStatus(statusRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar status")
    public ResponseEntity<StatusResponseDTO> atualizarStatus(@PathVariable Integer id, @Valid @RequestBody StatusRequestDTO statusRequestDTO){
        counter.increment();
        StatusResponseDTO status = statusService.atualizarStatus(id, statusRequestDTO);
        return ResponseEntity.ok().body(status);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir status")
    public ResponseEntity<Void> deletarStatus(@PathVariable Integer id){
        counter.increment();
        statusService.deletarStatus(id);
        return ResponseEntity.noContent().build();
    }
}
