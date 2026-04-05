package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.GeneroDTO;
import com.healthsys.pacientes.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    private final GeneroService generoService;


    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getGeneros(){
        List<GeneroDTO> generos = generoService.getGeneros();
        return ResponseEntity.ok().body(generos);
    }
}
