package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/vacinas")
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
    public ResponseEntity<List<VacinaResponseDTO>> getVacinas() {
        counter.increment();
        return ResponseEntity.ok(vacinaService.getVacinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> getVacinaById(@PathVariable UUID id) {
        counter.increment();
        return ResponseEntity.ok(vacinaService.getVacinaById(id));
    }

    @PostMapping
    public ResponseEntity<VacinaResponseDTO> criarVacina(
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        counter.increment();
        VacinaResponseDTO vacina = vacinaService.criarVacina(vacinaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> atualizarVacina(
            @PathVariable UUID id,
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        counter.increment();
        return ResponseEntity.ok(vacinaService.atualizarVacina(id, vacinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVacina(@PathVariable UUID id) {
        counter.increment();
        vacinaService.deletarVacina(id);
        return ResponseEntity.noContent().build();
    }
}