package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.AlergiaRequestDTO;
import com.healthsys.pacienteservice.dto.AlergiaResponseDTO;
import com.healthsys.pacienteservice.service.AlergiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alergias")
public class AlergiaController {
    private final AlergiaService alergiaService;

    public AlergiaController(AlergiaService alergiaService) {
        this.alergiaService = alergiaService;
    }

    @GetMapping
    public ResponseEntity<List<AlergiaResponseDTO>> getAlergias() {
        List<AlergiaResponseDTO> alergias = alergiaService.getAlergias();
        return ResponseEntity.ok().body(alergias);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AlergiaResponseDTO>> getAlergiasPorPaciente(@PathVariable UUID pacienteId) {
        List<AlergiaResponseDTO> alergias = alergiaService.getAlergiasPorPaciente(pacienteId);
        return ResponseEntity.ok().body(alergias);
    }

    @PostMapping
    public ResponseEntity<AlergiaResponseDTO> criarAlergia(
            @Valid @RequestBody AlergiaRequestDTO alergiaRequestDTO) {
        AlergiaResponseDTO alergiaResponseDTO = alergiaService.criarAlergia(alergiaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alergiaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlergiaResponseDTO> atualizarAlergia(
            @PathVariable Integer id,
            @Valid @RequestBody AlergiaRequestDTO alergiaRequestDTO) {
        AlergiaResponseDTO alergiaResponseDTO = alergiaService.atualizarAlergia(id, alergiaRequestDTO);
        return ResponseEntity.ok().body(alergiaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlergia(@PathVariable Integer id) {
        alergiaService.deletarAlergia(id);
        return ResponseEntity.noContent().build();
    }
}
