package com.healthsys.authservice.service;

import com.healthsys.authservice.dto.LoginRequestDTO;
import com.healthsys.authservice.model.Usuario;
import com.healthsys.authservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioService usuarioService, TokenService tokenService, PasswordEncoder passwordEncoder,  JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<Usuario> usuario = usuarioService.findByEmail(loginRequestDTO.getEmail());
        Optional<String> tokenOpt = usuario
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getSenha(),
                        u.getSenha()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getPerfil()));
        tokenOpt.ifPresent(token -> {
            String tokenHash = jwtUtil.getTokenHash(token);
            Instant issuedAt = jwtUtil.getExpiration(token);
            Instant expiresAt = jwtUtil.getExpiration(token);
            tokenService.salvarToken(tokenHash, usuario.get().getEmail(), issuedAt, expiresAt);
        });
        return tokenOpt;
    }

    public void logout(String token){
        try{
            Claims claims = jwtUtil.parseClaims(token);
            String tokenHash = claims.getId();

            tokenService.revoke(tokenHash);
        } catch (JwtException e){

        }
    }
    public boolean validateToken(String token){
        try{
            Claims claims = jwtUtil.parseClaims(token);
            String tokenHash = claims.getId();
            if (tokenService.isRevoked(tokenHash)) {
                return false;
            }
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
