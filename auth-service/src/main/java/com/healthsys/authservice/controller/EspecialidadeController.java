package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.EspecialidadeRequestDTO;
import com.healthsys.authservice.dto.EspecialidadeResponseDTO;
import com.healthsys.authservice.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;
    private final Counter counter;

    public EspecialidadeController(EspecialidadeService especialidadeService, MeterRegistry meterRegistry) {
        this.especialidadeService = especialidadeService;
        this.counter = Counter.builder("auth_service_requests_total")
                .description("Total de chamadas ao controller EspecialidadeController")
                .tag("controller", "EspecialidadeController")
                .tag("endpoint", "/especialidades")
                .register(meterRegistry);
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeResponseDTO>> getEspecialidades(){
        counter.increment();
        List<EspecialidadeResponseDTO> especialidades = especialidadeService.getEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    public ResponseEntity<EspecialidadeResponseDTO> createEspecialidade(@RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        counter.increment();
        EspecialidadeResponseDTO especialidadeResponseDTO = especialidadeService.criarEspecialidade(especialidadeRequestDTO);
        return ResponseEntity.ok(especialidadeResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable int id){
        counter.increment();
        especialidadeService.deletarEspecialidade(id);
        return ResponseEntity.noContent().build();
    }
}
