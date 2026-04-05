package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dto.PerfilResponseDTO;
import com.healthsys.usuarios.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
