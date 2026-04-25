package com.healthsys.triagemservice.controller;

import com.healthsys.triagemservice.dto.StatusRequestDTO;
import com.healthsys.triagemservice.dto.StatusResponseDTO;
import com.healthsys.triagemservice.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusResponseDTO>> getStatus(){
        List<StatusResponseDTO> status = statusService.getStatus();
        return ResponseEntity.ok().body(status);
    }

    @PostMapping
    public ResponseEntity<StatusResponseDTO> criarStatus(@Valid @RequestBody StatusRequestDTO statusRequestDTO){
        StatusResponseDTO status = statusService.criarStatus(statusRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> atualizarStatus(@PathVariable Integer id, @Valid @RequestBody StatusRequestDTO statusRequestDTO){
        StatusResponseDTO status = statusService.atualizarStatus(id, statusRequestDTO);
        return ResponseEntity.ok().body(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarStatus(@PathVariable Integer id){
        statusService.deletarStatus(id);
        return ResponseEntity.noContent().build();
    }
}
