package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.TelefoneRequestDTO;
import com.healthsys.pacientes.dto.TelefoneResponseDTO;
import com.healthsys.pacientes.service.TelefoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/telefones")
public class TelefoneController {
    private final TelefoneService telefoneService;

    public TelefoneController(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @GetMapping
    public ResponseEntity<List<TelefoneResponseDTO>> getTelefones() {
        List<TelefoneResponseDTO> telefones = telefoneService.getTelefones();
        return ResponseEntity.ok().body(telefones);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TelefoneResponseDTO>> getTelefonesPorPaciente(@PathVariable UUID pacienteId) {
        List<TelefoneResponseDTO> telefones = telefoneService.getTelefonesPorPaciente(pacienteId);
        return ResponseEntity.ok().body(telefones);
    }

    @PostMapping
    public ResponseEntity<TelefoneResponseDTO> criarTelefone(
            @Valid @RequestBody TelefoneRequestDTO telefoneRequestDTO) {
        TelefoneResponseDTO telefoneResponseDTO = telefoneService.criarTelefone(telefoneRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> atualizarTelefone(
            @PathVariable Integer id,
            @Valid @RequestBody TelefoneRequestDTO telefoneRequestDTO) {
        TelefoneResponseDTO telefoneResponseDTO = telefoneService.atualizarTelefone(id, telefoneRequestDTO);
        return ResponseEntity.ok().body(telefoneResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Integer id) {
        telefoneService.deletarTelefone(id);
        return ResponseEntity.noContent().build();
    }
}
