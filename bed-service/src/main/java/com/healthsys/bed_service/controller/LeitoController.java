package com.healthsys.bed_service.controller;

import com.healthsys.bed_service.model.Leito;
import com.healthsys.bed_service.service.LeitoService;
import com.healthsys.bed_service.repository.LeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitos")
@CrossOrigin(origins = "*")
public class LeitoController {

    @Autowired
    private LeitoService leitoService;

    @Autowired
    private LeitoRepository leitoRepository;

    @GetMapping
    public List<Leito> listarTodos() {
        return leitoRepository.findAll();
    }

    @PostMapping("/{id}/internar")
    public ResponseEntity<Leito> internar(@PathVariable Long id, @RequestParam String pacienteId) {
        return ResponseEntity.ok(leitoService.internarPaciente(id, pacienteId));
    }

    @PostMapping("/{id}/liberar")
    public ResponseEntity<Leito> liberar(@PathVariable Long id) {
        return ResponseEntity.ok(leitoService.liberarLeito(id));
    }
    
    @PostMapping
    public Leito criarLeito(@RequestBody Leito leito) {
        return leitoRepository.save(leito);
    }
}