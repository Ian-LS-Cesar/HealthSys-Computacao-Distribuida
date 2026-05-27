package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.PerfilRequestDTO;
import com.healthsys.authservice.dto.PerfilResponseDTO;
import com.healthsys.authservice.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/perfis")
@Tag(name = "API Perfis", description = "Endpoints para Consultar, Criar e Remover Perfis")
public class PerfilController {
    private final PerfilService perfilService;
    private final Counter counter;

    public PerfilController(PerfilService perfilService, MeterRegistry meterRegistry) {
        this.perfilService = perfilService;
        this.counter = Counter.builder("auth_service_requests_total")
                .description("Total de chamadas ao controller PerfilController")
                .tag("controller", "PerfilController")
                .tag("endpoint", "/perfis")
                .register(meterRegistry);
    }

    @GetMapping
    @Operation(summary = "Listar perfis")
    public ResponseEntity<List<PerfilResponseDTO>> getPerfis(){
        counter.increment();
        List<PerfilResponseDTO> perfis = perfilService.getPerfis();
        return ResponseEntity.ok().body(perfis);
    }

    @PostMapping
    @Operation(summary = "Criar perfil")
    public ResponseEntity<PerfilResponseDTO> createPerfis(@RequestBody PerfilRequestDTO perfilRequestDTO){
        counter.increment();
        PerfilResponseDTO perfilResponseDTO = perfilService.criarPerfis(perfilRequestDTO);
        return ResponseEntity.ok().body(perfilResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir perfil")
    public ResponseEntity<PerfilResponseDTO> deletePerfil(@PathVariable int id){
        counter.increment();
        perfilService.deletarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
