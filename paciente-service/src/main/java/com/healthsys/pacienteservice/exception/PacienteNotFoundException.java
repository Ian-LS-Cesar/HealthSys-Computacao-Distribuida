package com.healthsys.pacienteservice.exception;

public class PacienteNotFoundException extends RuntimeException{
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
