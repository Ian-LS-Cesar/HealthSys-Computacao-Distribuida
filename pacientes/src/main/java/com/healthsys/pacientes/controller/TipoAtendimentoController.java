package com.healthsys.pacientes.controller;

import com.healthsys.pacientes.dto.TipoAtendimentoRequestDTO;
import com.healthsys.pacientes.dto.TipoAtendimentoResponseDTO;
import com.healthsys.pacientes.service.TipoAtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-atendimento")
public class TipoAtendimentoController {
    private final TipoAtendimentoService tipoAtendimentoService;

    public TipoAtendimentoController(TipoAtendimentoService tipoAtendimentoService) {
        this.tipoAtendimentoService = tipoAtendimentoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoAtendimentoResponseDTO>> getTiposAtendimento() {
        List<TipoAtendimentoResponseDTO> tiposAtendimento = tipoAtendimentoService.getTiposAtendimento();
        return ResponseEntity.ok().body(tiposAtendimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAtendimentoResponseDTO> getTipoAtendimentoById(@PathVariable Integer id) {
        TipoAtendimentoResponseDTO tipoAtendimentoResponseDTO = tipoAtendimentoService.getTipoAtendimentoById(id);
        return ResponseEntity.ok().body(tipoAtendimentoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<TipoAtendimentoResponseDTO> criarTipoAtendimento(
            @Valid @RequestBody TipoAtendimentoRequestDTO tipoAtendimentoRequestDTO) {
        TipoAtendimentoResponseDTO tipoAtendimentoResponseDTO = tipoAtendimentoService.criarTipoAtendimento(tipoAtendimentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoAtendimentoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAtendimentoResponseDTO> atualizarTipoAtendimento(
            @PathVariable Integer id,
            @Valid @RequestBody TipoAtendimentoRequestDTO tipoAtendimentoRequestDTO) {
        TipoAtendimentoResponseDTO tipoAtendimentoResponseDTO = tipoAtendimentoService.atualizarTipoAtendimento(id, tipoAtendimentoRequestDTO);
        return ResponseEntity.ok().body(tipoAtendimentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoAtendimento(@PathVariable Integer id) {
        tipoAtendimentoService.deletarTipoAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}
