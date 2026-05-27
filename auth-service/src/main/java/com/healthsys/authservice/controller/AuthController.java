package com.healthsys.authservice.controller;

import com.healthsys.authservice.dto.LoginRequestDTO;
import com.healthsys.authservice.dto.LoginResponseDTO;
import com.healthsys.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@Tag(name = "API Autenticação", description = "Endpoints para Login, Logout e Validação de Token")
public class AuthController {
    private final AuthService authService;
    private final Counter counter;

    public AuthController(AuthService authService, MeterRegistry meterRegistry) {
        this.authService = authService;
        this.counter = Counter.builder("auth_service_requests_total")
                .description("Total de chamadas ao controller AuthController")
                .tag("controller", "AuthController")
                .tag("endpoint", "auth")
                .register(meterRegistry);
    }

    @Operation(summary = "Login do usuário")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO){
        counter.increment();

        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

        if(tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(summary = "Logout do usuário")
    @PostMapping("/logout")
    public ResponseEntity<LoginResponseDTO> logout(@RequestHeader("Authorization") String authHeader){
        counter.increment();

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String token = authHeader.substring(7);

        authService.logout(token);

        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Validar token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        counter.increment();

        // Authorization: Bear <token>

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
