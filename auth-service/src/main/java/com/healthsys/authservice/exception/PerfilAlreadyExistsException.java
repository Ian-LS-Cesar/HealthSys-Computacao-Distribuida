package com.healthsys.authservice.exception;


public class PerfilAlreadyExistsException extends RuntimeException{
    public PerfilAlreadyExistsException(String message) {
        super(message);
    }
}
