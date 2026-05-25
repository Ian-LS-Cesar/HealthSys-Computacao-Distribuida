package com.healthsys.medical_records_service.controller;

import com.healthsys.medical_records_service.model.Prontuario;
import com.healthsys.medical_records_service.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@CrossOrigin(origins = "*") // Permite a comunicação do app React
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    /**
     * Endpoint para iniciar a internação de um paciente em um leito específico.
     * POST http://localhost:8085/api/prontuarios/internar
     */
    @PostMapping("/internar")
    public ResponseEntity<Prontuario> internar(
            @RequestParam String pacienteId, 
            @RequestParam Long leitoId, 
            @RequestParam String diagnostico) {
        
        Prontuario novoProntuario = prontuarioService.internarPaciente(pacienteId, leitoId, diagnostico);
        return ResponseEntity.ok(novoProntuario);
    }

    /**
     * Endpoint para dar alta médica ao paciente e desocupar o leito correspondente.
     * POST http://localhost:8085/api/prontuarios/{id}/alta
     */
    @PostMapping("/{id}/alta")
    public ResponseEntity<Prontuario> darAlta(@PathVariable String id) {
        Prontuario prontuarioAtualizado = prontuarioService.darAltaPaciente(id);
        return ResponseEntity.ok(prontuarioAtualizado);
    }

    /**
     * Endpoint para listar todos os prontuários (histórico geral).
     * GET http://localhost:8085/api/prontuarios
     */
    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.listarTodosProntuarios());
    }

    /**
     * Endpoint para buscar um prontuário específico pelo ID.
     * GET http://localhost:8085/api/prontuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(prontuarioService.buscarProntuarioPorId(id));
    }

    /**
     * Endpoint para filtrar prontuários por status (ex: INTERNADO, ALTA).
     * GET http://localhost:8085/api/prontuarios/status?status=INTERNADO
     */
    @GetMapping("/status")
    public ResponseEntity<List<Prontuario>> buscarPorStatus(@RequestParam String status) {
        return ResponseEntity.ok(prontuarioService.buscarProntuariosPorStatus(status));
    }
}