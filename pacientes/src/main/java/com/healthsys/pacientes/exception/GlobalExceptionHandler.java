package com.healthsys.pacientes.exception;

import lombok.extern.slf4j.Slf4j;
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Requisição inválida: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Erro no valido", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePacienteNotFoundException(PacienteNotFoundException ex) {

        log.warn("Paciente não encontrado: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Paciente não encontrado");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCpfAlreadyExistsException(CpfAlreadyExistsException ex) {
        log.warn("CPF já existente: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "CPF já existente");
        return ResponseEntity.badRequest().body(errors);
    }
}
