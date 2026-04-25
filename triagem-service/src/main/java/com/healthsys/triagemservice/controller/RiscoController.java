package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.RiscoRequestDTO;
import com.healthsys.triagemservice.dto.RiscoResponseDTO;
import com.healthsys.triagemservice.service.RiscoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riscos")
public class RiscoController {
    private final RiscoService riscoService;

    public RiscoController(RiscoService riscoService){
        this.riscoService = riscoService;
    }

    @GetMapping
    public ResponseEntity<List<RiscoResponseDTO>> getRiscos(){
        List<RiscoResponseDTO> riscos = riscoService.getRiscos();
        return ResponseEntity.ok().body(riscos);
    }

    @PostMapping
    public ResponseEntity<RiscoResponseDTO> criarRisco(@Valid @RequestBody RiscoRequestDTO riscoRequestDTO){
        RiscoResponseDTO riscoResponseDTO = riscoService.criarRisco(riscoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(riscoResponseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RiscoResponseDTO> atualizarRisco(@PathVariable Integer id, @Valid @RequestBody RiscoRequestDTO riscoRequestDTO){
        RiscoResponseDTO riscoResponseDTO = riscoService.atualizarRisco(id, riscoRequestDTO);
        return ResponseEntity.ok().body(riscoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRisco(@PathVariable Integer id){
        riscoService.deletarRisco(id);
        return ResponseEntity.noContent().build();
    }
}
