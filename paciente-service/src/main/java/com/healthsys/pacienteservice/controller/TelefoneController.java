package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.TelefoneRequestDTO;
import com.healthsys.pacienteservice.dto.TelefoneResponseDTO;
import com.healthsys.pacienteservice.service.TelefoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
    private final TelefoneService telefoneService;
    private final Counter counter;

    public TelefoneController(TelefoneService telefoneService, MeterRegistry meterRegistry) {
        this.telefoneService = telefoneService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller TelefoneController")
                .tag("controller", "TelefoneController")
                .tag("endpoint", "/telefones")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<TelefoneResponseDTO>> getTelefones() {
        counter.increment();
        List<TelefoneResponseDTO> telefones = telefoneService.getTelefones();
        return ResponseEntity.ok().body(telefones);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TelefoneResponseDTO>> getTelefonesPorPaciente(@PathVariable UUID pacienteId) {
        counter.increment();
        List<TelefoneResponseDTO> telefones = telefoneService.getTelefonesPorPaciente(pacienteId);
        return ResponseEntity.ok().body(telefones);
    }

    @PostMapping
    public ResponseEntity<TelefoneResponseDTO> criarTelefone(
            @Valid @RequestBody TelefoneRequestDTO telefoneRequestDTO) {
        counter.increment();
        TelefoneResponseDTO telefoneResponseDTO = telefoneService.criarTelefone(telefoneRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> atualizarTelefone(
            @PathVariable Integer id,
            @Valid @RequestBody TelefoneRequestDTO telefoneRequestDTO) {
        counter.increment();
        TelefoneResponseDTO telefoneResponseDTO = telefoneService.atualizarTelefone(id, telefoneRequestDTO);
        return ResponseEntity.ok().body(telefoneResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Integer id) {
        counter.increment();
        telefoneService.deletarTelefone(id);
        return ResponseEntity.noContent().build();
    }
}
