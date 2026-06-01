package com.healthsys.bed_service.controller;

import com.healthsys.bed_service.model.Leito;
import com.healthsys.bed_service.service.LeitoService;
import com.healthsys.bed_service.repository.LeitoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitos")
@Tag(name = "API Leitos", description = "Endpoints para Consultar, Criar, Internar e Liberar Leitos")
public class LeitoController {

    @Autowired
    private LeitoService leitoService;

    @Autowired
    private LeitoRepository leitoRepository;

    @GetMapping
    @Operation(summary = "Listar leitos")
    public List<Leito> listarTodos() {
        return leitoRepository.findAll();
    }

    @PostMapping("/{id}/internar")
    @Operation(summary = "Internar paciente no leito")
    public ResponseEntity<Leito> internar(@PathVariable Long id, @RequestParam String pacienteId) {
        return ResponseEntity.ok(leitoService.internarPaciente(id, pacienteId));
    }

    @PostMapping("/{id}/liberar")
    @Operation(summary = "Liberar leito")
    public ResponseEntity<Leito> liberar(@PathVariable Long id) {
        return ResponseEntity.ok(leitoService.liberarLeito(id));
    }
    
    @PostMapping
    @Operation(summary = "Criar leito")
    public Leito criarLeito(@RequestBody Leito leito) {
        return leitoRepository.save(leito);
    }

    @PostMapping("/{id}/higienizar")
    @Operation(summary = "Marcar leito como livre após higienização")
    public ResponseEntity<Leito> higienizar(@PathVariable Long id) {
        return ResponseEntity.ok(leitoService.higienizarLeito(id));
    }
}