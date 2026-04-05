package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.TelefoneDTO;
import com.healthsys.pacientes.service.GeneroService;
import com.healthsys.pacientes.service.TelefoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
    private final TelefoneService telefoneService;

    public TelefoneController(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> getTelefones(){
        List<TelefoneDTO> telefones = telefoneService.getTelefones();
        return ResponseEntity.ok().body(telefones);
    }
}
