package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.TriagemRequestDTO;
import com.healthsys.triagemservice.dto.TriagemResponseDTO;
import com.healthsys.triagemservice.service.TriagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/triagens")
public class TriagemController {
    private final TriagemService triagemService;

    public TriagemController(TriagemService triagemService) {
        this.triagemService = triagemService;
    }

    @GetMapping
    public ResponseEntity<List<TriagemResponseDTO>> getTriagens(){
        return ResponseEntity.ok().body(triagemService.getTriagens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TriagemResponseDTO> getTriagem(@PathVariable UUID id){
        return ResponseEntity.ok(triagemService.getTriagemById(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TriagemResponseDTO>> getTriagemByPaciente(@PathVariable UUID pacienteId){
        return ResponseEntity.ok(triagemService.getTriagemByPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<TriagemResponseDTO> criarTriagem(@RequestBody TriagemRequestDTO triagemRequestDTO){
        TriagemResponseDTO triagem = triagemService.criarTriagem(triagemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(triagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TriagemResponseDTO> atualizarTriagem(@PathVariable UUID id, @Valid @RequestBody TriagemRequestDTO triagemRequestDTO){
        TriagemResponseDTO triagem = triagemService.atualizarTriagem(id, triagemRequestDTO);
        return ResponseEntity.ok().body(triagem);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTriagem(@PathVariable UUID id){
        triagemService.deletarTriagem(id);
        return ResponseEntity.noContent().build();
    }
}
