package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.LoginRequestDTO;
import com.healthsys.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioService usuarioService, PasswordEncoder passwordEncoder,  JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<String> token = usuarioService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getSenha(),
                        u.getSenha()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getPerfil()));
        return token;
    }
}
