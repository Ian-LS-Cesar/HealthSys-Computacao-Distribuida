package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.SintomaRequestDTO;
import com.healthsys.triagemservice.dto.SintomaResponseDTO;
import com.healthsys.triagemservice.service.SintomaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sintomas")
public class SintomaController {
    private final SintomaService sintomaService;

    public SintomaController(SintomaService sintomaService) {
        this.sintomaService = sintomaService;
    }

    @GetMapping
    public ResponseEntity<List<SintomaResponseDTO>> getSintomas() {
        List<SintomaResponseDTO> sintomas = sintomaService.getSintomas();
        return ResponseEntity.ok().body(sintomas);
    }

    @PostMapping
    public ResponseEntity<SintomaResponseDTO> criarSintoma(@Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        SintomaResponseDTO sintomaResponseDTO = sintomaService.criarSintoma(sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SintomaResponseDTO> atualizarSintoma(@PathVariable Integer id, @Valid @RequestBody SintomaRequestDTO sintomaRequestDTO) {
        SintomaResponseDTO sintomaResponseDTO = sintomaService.atualizarSintoma(id, sintomaRequestDTO);
        return ResponseEntity.ok().body(sintomaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSintoma(@PathVariable Integer id) {
        sintomaService.deletarSintoma(id);
        return ResponseEntity.noContent().build();
    }
}
