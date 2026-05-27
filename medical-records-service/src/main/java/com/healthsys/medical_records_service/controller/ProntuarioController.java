package com.healthsys.medical_records_service.controller;

import com.healthsys.medical_records_service.model.Prontuario;
import com.healthsys.medical_records_service.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@CrossOrigin(origins = "*") // Permite a comunicação do app React
@Tag(name = "API Prontuários", description = "Endpoints para Internação, Alta, Consulta e Filtro de Prontuários")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    /**
     * Endpoint para iniciar a internação de um paciente em um leito específico.
     */
    @PostMapping("/internar")
    @Operation(summary = "Internar paciente")
    public ResponseEntity<Prontuario> internar(
            @RequestParam String pacienteId, 
            @RequestParam Long leitoId, 
            @RequestParam String diagnostico) {
        
        Prontuario novoProntuario = prontuarioService.internarPaciente(pacienteId, leitoId, diagnostico);
        return ResponseEntity.ok(novoProntuario);
    }

    /**
     * Endpoint para dar alta médica ao paciente e desocupar o leito correspondente.
     */
    @PostMapping("/{id}/alta")
    @Operation(summary = "Dar alta ao paciente")
    public ResponseEntity<Prontuario> darAlta(@PathVariable String id) {
        Prontuario prontuarioAtualizado = prontuarioService.darAltaPaciente(id);
        return ResponseEntity.ok(prontuarioAtualizado);
    }

    /**
     * Endpoint para listar todos os prontuários (histórico geral).
     */
    @GetMapping
    @Operation(summary = "Listar prontuários")
    public ResponseEntity<List<Prontuario>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.listarTodosProntuarios());
    }

    /**
     * Endpoint para buscar um prontuário específico pelo ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar prontuário por ID")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(prontuarioService.buscarProntuarioPorId(id));
    }

    /**
     * Endpoint para filtrar prontuários por status (ex: INTERNADO, ALTA).
     */
    @GetMapping("/status")
    @Operation(summary = "Buscar prontuários por status")
    public ResponseEntity<List<Prontuario>> buscarPorStatus(@RequestParam String status) {
        return ResponseEntity.ok(prontuarioService.buscarProntuariosPorStatus(status));
    }
}