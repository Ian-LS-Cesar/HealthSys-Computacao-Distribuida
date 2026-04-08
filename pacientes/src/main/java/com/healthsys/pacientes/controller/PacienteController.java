package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.PacienteRequestDTO;
import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.dto.validators.CreatePacienteValidationGroup;
import com.healthsys.pacientes.service.PacienteService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.getPacientes();
        return ResponseEntity.ok().body(pacientes);
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criarPaciente(@Validated({Default.class, CreatePacienteValidationGroup.class}) @RequestBody PacienteRequestDTO pacienteRequestDTO) {
        PacienteResponseDTO pacienteResponseDTO = pacienteService.criarPaciente(pacienteRequestDTO);
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<PacienteResponseDTO> updateUsuario(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PacienteRequestDTO usuarioRequestDTO){
        PacienteResponseDTO pacienteResponseDTO = pacienteService.atualizarPaciente(id, usuarioRequestDTO);
        return ResponseEntity.ok().body(pacienteResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> deletarPaciente(@PathVariable UUID id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
