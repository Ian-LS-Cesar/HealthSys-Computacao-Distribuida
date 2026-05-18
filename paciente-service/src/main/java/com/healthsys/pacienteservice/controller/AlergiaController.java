package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.AlergiaRequestDTO;
import com.healthsys.pacienteservice.dto.AlergiaResponseDTO;
import com.healthsys.pacienteservice.service.AlergiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {
    private final AlergiaService alergiaService;
    private final Counter counter;

    public AlergiaController(AlergiaService alergiaService, MeterRegistry meterRegistry) {
        this.alergiaService = alergiaService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller AlergiaController")
                .tag("controller", "AlergiaController")
                .tag("endpoint", "/alergias")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<AlergiaResponseDTO>> getAlergias() {
        counter.increment();
        List<AlergiaResponseDTO> alergias = alergiaService.getAlergias();
        return ResponseEntity.ok().body(alergias);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AlergiaResponseDTO>> getAlergiasPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        List<AlergiaResponseDTO> alergias = alergiaService.getAlergiasPorPaciente(pacienteId);
        return ResponseEntity.ok().body(alergias);
    }

    @PostMapping
    public ResponseEntity<AlergiaResponseDTO> criarAlergia(
            @Valid @RequestBody AlergiaRequestDTO alergiaRequestDTO) {
        counter.increment();
        AlergiaResponseDTO alergiaResponseDTO = alergiaService.criarAlergia(alergiaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alergiaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlergiaResponseDTO> atualizarAlergia(
            @PathVariable Integer id,
            @Valid @RequestBody AlergiaRequestDTO alergiaRequestDTO) {
        counter.increment();
        AlergiaResponseDTO alergiaResponseDTO = alergiaService.atualizarAlergia(id, alergiaRequestDTO);
        return ResponseEntity.ok().body(alergiaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlergia(@PathVariable Integer id) {
        counter.increment();
        alergiaService.deletarAlergia(id);
        return ResponseEntity.noContent().build();
    }
}
