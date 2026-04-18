package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping
    public ResponseEntity<List<VacinaResponseDTO>> getVacinas() {
        return ResponseEntity.ok(vacinaService.getVacinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> getVacinaById(@PathVariable UUID id) {
        return ResponseEntity.ok(vacinaService.getVacinaById(id));
    }

    @PostMapping
    public ResponseEntity<VacinaResponseDTO> criarVacina(
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        VacinaResponseDTO vacina = vacinaService.criarVacina(vacinaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> atualizarVacina(
            @PathVariable UUID id,
            @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO) {
        return ResponseEntity.ok(vacinaService.atualizarVacina(id, vacinaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVacina(@PathVariable UUID id) {
        vacinaService.deletarVacina(id);
        return ResponseEntity.noContent().build();
    }
}