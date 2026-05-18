package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.SintomaRequestDTO;
import com.healthsys.triagemservice.dto.SintomaResponseDTO;
import com.healthsys.triagemservice.service.SintomaService;
import jakarta.validation.Valid;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sintomas")
public class SintomaController {
    private final SintomaService sintomaService;
    private final Counter counter;

    public SintomaController(SintomaService sintomaService, MeterRegistry meterRegistry) {
        this.sintomaService = sintomaService;
        this.counter = Counter.builder("triagem_service_requests_total")
                .description("Total de chamadas ao controller SintomaController")
                .tag("controller", "SintomaController")
                .tag("endpoint", "/sintomas")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<SintomaResponseDTO>> getSintomas() {
        counter.increment();
        List<SintomaResponseDTO> sintomas = sintomaService.getSintomas();
        return ResponseEntity.ok().body(sintomas);
    }

    @PostMapping
    public ResponseEntity<SintomaResponseDTO> criarSintoma(@Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        counter.increment();
        SintomaResponseDTO sintomaResponseDTO = sintomaService.criarSintoma(sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SintomaResponseDTO> atualizarSintoma(@PathVariable Integer id, @Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        counter.increment();
        SintomaResponseDTO sintomaResponseDTO = sintomaService.atualizarSintoma(id, sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSintoma(@PathVariable Integer id) {
        counter.increment();
        sintomaService.deletarSintoma(id);
        return ResponseEntity.noContent().build();
    }
}
