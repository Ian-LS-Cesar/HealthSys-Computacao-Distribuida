package com.healthsys.notification_service.controller;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Endpoint para disparar uma notificação de teste diretamente para a fila.
     * Útil para validar se o Consumer está processando as mensagens corretamente.
     */
    @PostMapping("/send-test")
    public ResponseEntity<String> sendTestNotification(@RequestBody Notification notification) {
        try {
            // Converte o objeto Java para JSON e envia para a fila configurada
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, notification);
            return ResponseEntity.ok("Notificação enviada para a fila de processamento!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Erro ao enviar para o RabbitMQ: " + e.getMessage());
        }
    }

    /**
     * Endpoint simples para verificar se o serviço está online.
     */
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Notification Service está operacional na rede healthsys-internal.");
    }
}