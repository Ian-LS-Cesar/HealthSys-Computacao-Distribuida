package com.healthsys.pacientes.exception;

public class PacienteNotFoundException extends RuntimeException{
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
