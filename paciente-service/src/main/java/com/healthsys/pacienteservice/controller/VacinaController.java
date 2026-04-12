package com.healthsys.pacienteservice.controller;

import com.healthsys.pacienteservice.dto.VacinaRequestDTO;
import com.healthsys.pacienteservice.dto.VacinaResponseDTO;
import com.healthsys.pacienteservice.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vacinas")
public class VacinaController {
    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping
    public ResponseEntity<List<VacinaResponseDTO>> getVacinas(){
        List<VacinaResponseDTO> vacinas = vacinaService.getVacinas();
        return ResponseEntity.ok().body(vacinas);
    }

    @GetMapping("/paciente/{paciente}")
    public ResponseEntity<List<VacinaResponseDTO>> getVacinasPorPaciente(@PathVariable UUID paciente){
        List<VacinaResponseDTO> vacinas = vacinaService.getVacinasPorPaciente(paciente);
        return ResponseEntity.ok().body(vacinas);
    }

    @PostMapping()
    public ResponseEntity<VacinaResponseDTO> criarVacina(@Valid @RequestBody VacinaRequestDTO vacinaRequestDTO){
        VacinaResponseDTO vacina = vacinaService.criarVacina(vacinaRequestDTO);
        return ResponseEntity.ok().body(vacina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> atualizarVacina(@PathVariable UUID id, @Valid @RequestBody VacinaRequestDTO vacinaRequestDTO){
        VacinaResponseDTO vacinaResponseDTO = vacinaService.atualizarVacina(id, vacinaRequestDTO);
        return ResponseEntity.ok().body(vacinaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> deletarVacina(@PathVariable UUID id){
        vacinaService.deletarVacina(id);
        return ResponseEntity.noContent().build();
    }



}
