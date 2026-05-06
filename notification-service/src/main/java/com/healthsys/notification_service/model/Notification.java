package com.healthsys.notification_service.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // Gera Getters, Setters, toString, equals e hashCode
@AllArgsConstructor // Gera construtor com todos os campos
@NoArgsConstructor  // Gera construtor vazio (exigido pelo Jackson/RabbitMQ)
public class Notification implements Serializable {
    private String title;
    private String message;
    private String recipient;
    private String priority;
    private LocalDateTime timestamp = LocalDateTime.now();
}