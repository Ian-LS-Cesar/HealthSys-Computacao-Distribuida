package com.healthsys.authservice.exception;

import com.healthsys.authservice.service.AuthService;

public class PerfilAlreadyExistsException extends RuntimeException{
    public PerfilAlreadyExistsException(String message) {
        super(message);
    }
}
