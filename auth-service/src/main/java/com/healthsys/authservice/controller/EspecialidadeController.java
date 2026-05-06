package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.EspecialidadeRequestDTO;
import com.healthsys.authservice.dto.EspecialidadeResponseDTO;
import com.healthsys.authservice.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadeResponseDTO>> getEspecialidades(){
        List<EspecialidadeResponseDTO> especialidades = especialidadeService.getEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    public ResponseEntity<EspecialidadeResponseDTO> createEspecialidade(@RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        EspecialidadeResponseDTO especialidadeResponseDTO = especialidadeService.criarEspecialidade(especialidadeRequestDTO);
        return ResponseEntity.ok(especialidadeResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable int id){
        especialidadeService.deletarEspecialidade(id);
        return ResponseEntity.noContent().build();
    }
}
