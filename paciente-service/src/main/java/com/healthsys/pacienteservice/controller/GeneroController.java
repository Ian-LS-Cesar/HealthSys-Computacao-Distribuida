package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.GeneroRequestDTO;
import com.healthsys.pacienteservice.dto.GeneroResponseDTO;
import com.healthsys.pacienteservice.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {
    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroResponseDTO>> getGeneros() {
        List<GeneroResponseDTO> generos = generoService.getGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<GeneroResponseDTO> criarGenero(@Valid @RequestBody GeneroRequestDTO generoRequestDTO) {
        GeneroResponseDTO generoResponseDTO = generoService.criarGenero(generoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> atualizarGenero(
            @PathVariable Integer id,
            @Valid @RequestBody GeneroRequestDTO generoRequestDTO) {
        GeneroResponseDTO generoResponseDTO = generoService.atualizarGenero(id, generoRequestDTO);
        return ResponseEntity.ok().body(generoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Integer id) {
        generoService.deletarGenero(id);
        return ResponseEntity.noContent().build();
    }
}
