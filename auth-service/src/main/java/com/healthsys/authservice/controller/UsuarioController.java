package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.UsuarioRequestDTO;
import com.healthsys.authservice.dto.UsuarioResponseDTO;
import com.healthsys.authservice.dto.validators.CreateUsuarioValidationGroup;
import com.healthsys.authservice.service.UsuarioService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuariosByPerfilId(@PathVariable Integer perfilId) {
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuariosByPerfilId(perfilId);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/especialidade/{especialidadeId}")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuariosByEspecialidadeId(@PathVariable Integer especialidadeId) {
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuariosByEspecialidadeId(especialidadeId);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioByEmail(@PathVariable String email) {
        UsuarioResponseDTO usuario = usuarioService.getUsuarioByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable UUID id) {
        UsuarioResponseDTO usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(
            @Validated({Default.class, CreateUsuarioValidationGroup.class})
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.criarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @PathVariable UUID id,
            @Validated({Default.class})
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}