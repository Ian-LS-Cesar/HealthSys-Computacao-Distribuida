package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.PerfilRequestDTO;
import com.healthsys.authservice.dto.PerfilResponseDTO;
import com.healthsys.authservice.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> getPerfis(){
        List<PerfilResponseDTO> perfis = perfilService.getPerfis();
        return ResponseEntity.ok().body(perfis);
    }

    @PostMapping
    public ResponseEntity<PerfilResponseDTO> createPerfis(@RequestBody PerfilRequestDTO perfilRequestDTO){
        PerfilResponseDTO perfilResponseDTO = perfilService.criarPerfis(perfilRequestDTO);
        return ResponseEntity.ok().body(perfilResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> deletePerfil(@PathVariable int id){
        perfilService.deletarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
