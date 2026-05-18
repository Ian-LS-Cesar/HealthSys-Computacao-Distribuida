package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.RiscoRequestDTO;
import com.healthsys.triagemservice.dto.RiscoResponseDTO;
import com.healthsys.triagemservice.service.RiscoService;
import jakarta.validation.Valid;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riscos")
public class RiscoController {
    private final RiscoService riscoService;
    private final Counter counter;

    public RiscoController(RiscoService riscoService, MeterRegistry meterRegistry){
        this.riscoService = riscoService;
        this.counter = Counter.builder("triagem_service_requests_total")
                .description("Total de chamadas ao controller RiscoController")
                .tag("controller", "RiscoController")
                .tag("endpoint", "/riscos")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<RiscoResponseDTO>> getRiscos(){
        counter.increment();
        List<RiscoResponseDTO> riscos = riscoService.getRiscos();
        return ResponseEntity.ok().body(riscos);
    }

    @PostMapping
    public ResponseEntity<RiscoResponseDTO> criarRisco(@Valid @RequestBody RiscoRequestDTO riscoRequestDTO){
        counter.increment();
        RiscoResponseDTO riscoResponseDTO = riscoService.criarRisco(riscoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(riscoResponseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RiscoResponseDTO> atualizarRisco(@PathVariable Integer id, @Valid @RequestBody RiscoRequestDTO riscoRequestDTO){
        counter.increment();
        RiscoResponseDTO riscoResponseDTO = riscoService.atualizarRisco(id, riscoRequestDTO);
        return ResponseEntity.ok().body(riscoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRisco(@PathVariable Integer id){
        counter.increment();
        riscoService.deletarRisco(id);
        return ResponseEntity.noContent().build();
    }
}
