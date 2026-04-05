package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.PacienteResponseDTO;
import com.healthsys.pacientes.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes(){
        List<PacienteResponseDTO> pacientes = pacienteService.getPacientes();
        return ResponseEntity.ok().body(pacientes);
    }
}
