package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.SintomaRequestDTO;
import com.healthsys.triagemservice.dto.SintomaResponseDTO;
import com.healthsys.triagemservice.service.SintomaService;
import jakarta.validation.Valid;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sintomas")
@Tag(name = "API Sintomas", description = "Endpoints para Consultar, Criar, Atualizar e Remover Sintomas")
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
    @Operation(summary = "Listar sintomas")
    public ResponseEntity<List<SintomaResponseDTO>> getSintomas() {
        counter.increment();
        List<SintomaResponseDTO> sintomas = sintomaService.getSintomas();
        return ResponseEntity.ok().body(sintomas);
    }

    @PostMapping
    @Operation(summary = "Criar sintoma")
    public ResponseEntity<SintomaResponseDTO> criarSintoma(@Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        counter.increment();
        SintomaResponseDTO sintomaResponseDTO = sintomaService.criarSintoma(sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar sintoma")
    public ResponseEntity<SintomaResponseDTO> atualizarSintoma(@PathVariable Integer id, @Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        counter.increment();
        SintomaResponseDTO sintomaResponseDTO = sintomaService.atualizarSintoma(id, sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir sintoma")
    public ResponseEntity<Void> deletarSintoma(@PathVariable Integer id) {
        counter.increment();
        sintomaService.deletarSintoma(id);
        return ResponseEntity.noContent().build();
    }
}
