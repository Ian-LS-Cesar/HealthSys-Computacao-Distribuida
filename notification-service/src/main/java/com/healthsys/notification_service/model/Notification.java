package com.healthsys.notification_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notification implements Serializable {
    private String title;
    private String message;
    private String recipient;
    private String priority;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Notification() {}
    // Getters e Setters aqui
}