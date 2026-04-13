package com.healthsys.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>>  handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){

        log.warn("Email já existe {}", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Endereço de e-mail já existe");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePacienteNotFoundException(UsuarioNotFoundException ex) {

        log.warn("Paciente não encontrado: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Paciente não encontrado");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PerfilAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlePerfilAlreadyExistsException(PerfilAlreadyExistsException ex){
        log.warn("Perfil já existe: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }
}
