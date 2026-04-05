package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.SexoResponseDTO;
import com.healthsys.pacientes.service.SexoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sexos")
public class SexoController {
    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping
    public ResponseEntity<List<SexoResponseDTO>> getSexos(){
        List<SexoResponseDTO> sexos = sexoService.getGeneros();
        return ResponseEntity.ok().body(sexos);
    }
}
