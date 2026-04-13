package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.EnderecoRequestDTO;
import com.healthsys.pacienteservice.dto.EnderecoResponseDTO;
import com.healthsys.pacienteservice.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos() {
        List<EnderecoResponseDTO> enderecos = enderecoService.getEnderecos();
        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecosPorPaciente(@PathVariable UUID pacienteId) {
        List<EnderecoResponseDTO> enderecos = enderecoService.getEnderecosPorPaciente(pacienteId);
        return ResponseEntity.ok().body(enderecos);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criarEndereco(
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.criarEndereco(enderecoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizarEndereco(
            @PathVariable Integer id,
            @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.atualizarEndereco(id, enderecoRequestDTO);
        return ResponseEntity.ok().body(enderecoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
