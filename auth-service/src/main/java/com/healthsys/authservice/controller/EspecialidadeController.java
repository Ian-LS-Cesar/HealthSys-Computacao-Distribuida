package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.EspecialidadeRequestDTO;
import com.healthsys.authservice.dto.EspecialidadeResponseDTO;
import com.healthsys.authservice.service.EspecialidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/especialidades")
@Tag(name = "API Especialidades", description = "Endpoints para Consultar, Criar e Remover Especialidades")
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
    @Operation(summary = "Listar especialidades")
    public ResponseEntity<List<EspecialidadeResponseDTO>> getEspecialidades(){
        counter.increment();
        List<EspecialidadeResponseDTO> especialidades = especialidadeService.getEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    @Operation(summary = "Criar especialidade")
    public ResponseEntity<EspecialidadeResponseDTO> createEspecialidade(@RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        counter.increment();
        EspecialidadeResponseDTO especialidadeResponseDTO = especialidadeService.criarEspecialidade(especialidadeRequestDTO);
        return ResponseEntity.ok(especialidadeResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir especialidade")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable int id){
        counter.increment();
        especialidadeService.deletarEspecialidade(id);
        return ResponseEntity.noContent().build();
    }
}
