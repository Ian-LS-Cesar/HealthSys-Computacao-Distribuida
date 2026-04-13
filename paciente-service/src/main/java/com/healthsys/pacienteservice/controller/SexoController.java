package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.SexoRequestDTO;
import com.healthsys.pacienteservice.dto.SexoResponseDTO;
import com.healthsys.pacienteservice.service.SexoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sexos")
public class SexoController {
    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping
    public ResponseEntity<List<SexoResponseDTO>> getSexos() {
        List<SexoResponseDTO> sexos = sexoService.getSexos();
        return ResponseEntity.ok().body(sexos);
    }

    @PostMapping
    public ResponseEntity<SexoResponseDTO> criarSexo(@Valid @RequestBody SexoRequestDTO sexoRequestDTO) {
        SexoResponseDTO sexoResponseDTO = sexoService.criarSexo(sexoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sexoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SexoResponseDTO> atualizarSexo(
            @PathVariable Integer id,
            @Valid @RequestBody SexoRequestDTO sexoRequestDTO) {
        SexoResponseDTO sexoResponseDTO = sexoService.atualizarSexo(id, sexoRequestDTO);
        return ResponseEntity.ok().body(sexoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSexo(@PathVariable Integer id) {
        sexoService.deletarSexo(id);
        return ResponseEntity.noContent().build();
    }
}
