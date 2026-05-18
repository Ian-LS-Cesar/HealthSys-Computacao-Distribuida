package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.GeneroRequestDTO;
import com.healthsys.pacienteservice.dto.GeneroResponseDTO;
import com.healthsys.pacienteservice.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    private final GeneroService generoService;
    private final Counter counter;

    public GeneroController(GeneroService generoService, MeterRegistry meterRegistry) {
        this.generoService = generoService;
        this.counter = Counter.builder("paciente_service_requests_total")
                .description("Total de chamadas ao controller GeneroController")
                .tag("controller", "GeneroController")
                .tag("endpoint", "/generos")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<GeneroResponseDTO>> getGeneros() {
        counter.increment();
        List<GeneroResponseDTO> generos = generoService.getGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroResponseDTO> criarGenero(@Valid @RequestBody GeneroRequestDTO generoRequestDTO) {
        counter.increment();
        GeneroResponseDTO generoResponseDTO = generoService.criarGenero(generoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> atualizarGenero(
            @PathVariable Integer id,
            @Valid @RequestBody GeneroRequestDTO generoRequestDTO) {
        counter.increment();
        GeneroResponseDTO generoResponseDTO = generoService.atualizarGenero(id, generoRequestDTO);
        return ResponseEntity.ok().body(generoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Integer id) {
        counter.increment();
        generoService.deletarGenero(id);
        return ResponseEntity.noContent().build();
    }
}
