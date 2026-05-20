package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.TriagemDetalhadaResponseDTO;
import com.healthsys.triagemservice.dto.TriagemRequestDTO;
import com.healthsys.triagemservice.dto.TriagemResponseDTO;
import com.healthsys.triagemservice.service.TriagemService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/triagens")
public class TriagemController {
    private final TriagemService triagemService;
    private final Counter counter;

    public TriagemController(TriagemService triagemService, MeterRegistry meterRegistry) {
        this.triagemService = triagemService;
        this.counter = Counter.builder("triagem_service_requests_total")
                .description("Total de chamadas ao controller TriagemController")
                .tag("controller", "TriagemController")
                .tag("endpoint", "/triagens")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<TriagemResponseDTO>> getTriagens() {
        counter.increment();
        return ResponseEntity.ok(triagemService.getTriagens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TriagemResponseDTO> getTriagem(@PathVariable UUID id) {
        counter.increment();
        return ResponseEntity.ok(triagemService.getTriagemById(id));
    }

    @GetMapping("/{id}/detalhada")
    public ResponseEntity<TriagemDetalhadaResponseDTO> getTriagemDetalhada(@PathVariable UUID id) {
        counter.increment();
        return ResponseEntity.ok(triagemService.getTriagemDetalhada(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TriagemResponseDTO>> getTriagemByPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        return ResponseEntity.ok(triagemService.getTriagemByPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<TriagemResponseDTO> criarTriagem(@Valid @RequestBody TriagemRequestDTO triagemRequestDTO) {
        counter.increment();
        TriagemResponseDTO triagem = triagemService.criarTriagem(triagemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(triagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TriagemResponseDTO> atualizarTriagem(
            @PathVariable UUID id,
            @Valid @RequestBody TriagemRequestDTO triagemRequestDTO) {
        counter.increment();
        TriagemResponseDTO triagem = triagemService.atualizarTriagem(id, triagemRequestDTO);
        return ResponseEntity.ok(triagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTriagem(@PathVariable UUID id) {
        counter.increment();
        triagemService.deletarTriagem(id);
        return ResponseEntity.noContent().build();
    }
}