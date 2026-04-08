package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dto.PerfilRequestDTO;
import com.healthsys.usuarios.dto.PerfilResponseDTO;
import com.healthsys.usuarios.service.PerfilService;
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
}
