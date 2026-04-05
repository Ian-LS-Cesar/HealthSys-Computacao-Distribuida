package com.healthsys.usuarios.controller;

import com.healthsys.usuarios.dto.UsuarioResponseDTO;
import com.healthsys.usuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuarios(){
        List<UsuarioResponseDTO> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
}
