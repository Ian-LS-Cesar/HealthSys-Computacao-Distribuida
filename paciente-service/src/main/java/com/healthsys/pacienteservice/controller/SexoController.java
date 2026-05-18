package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.SexoRequestDTO;
import com.healthsys.pacienteservice.dto.SexoResponseDTO;
import com.healthsys.pacienteservice.service.SexoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/sexos")
public class SexoController {
    private final SexoService sexoService;
    private final Counter counter;

    public SexoController(SexoService sexoService, MeterRegistry meterRegistry) {
        this.sexoService = sexoService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller SexoController")
                .tag("controller", "SexoController")
                .tag("endpoint", "/sexos")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<SexoResponseDTO>> getSexos() {
        counter.increment();
        List<SexoResponseDTO> sexos = sexoService.getSexos();
        return ResponseEntity.ok().body(sexos);
    }

    @PostMapping
    public ResponseEntity<SexoResponseDTO> criarSexo(@Valid @RequestBody SexoRequestDTO sexoRequestDTO) {
        counter.increment();
        SexoResponseDTO sexoResponseDTO = sexoService.criarSexo(sexoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sexoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SexoResponseDTO> atualizarSexo(
            @PathVariable Integer id,
            @Valid @RequestBody SexoRequestDTO sexoRequestDTO) {
        counter.increment();
        SexoResponseDTO sexoResponseDTO = sexoService.atualizarSexo(id, sexoRequestDTO);
        return ResponseEntity.ok().body(sexoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSexo(@PathVariable Integer id) {
        counter.increment();
        sexoService.deletarSexo(id);
        return ResponseEntity.noContent().build();
    }
}
