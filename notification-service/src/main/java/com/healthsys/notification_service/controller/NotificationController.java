package com.healthsys.notification_service.controller;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@RestController
@RequestMapping("/notifications")
@Tag(name = "API Notificações", description = "Endpoints para Stream de Notificações, Envio de Teste e Status do Serviço")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;
    private final Counter counter;
    private final Map<String, List<SseEmitter>> specialtyEmitters = new ConcurrentHashMap<>();

    public NotificationController(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.counter = Counter.builder("notification_service_requests_total")
                .description("Total de chamadas ao controller NotificationController")
                .tag("controller", "NotificationController")
                .tag("endpoint", "/api/notifications")
                .register(meterRegistry);
    }

    @GetMapping(value = "/stream/{especialidade}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Abrir stream de notificações")
    public SseEmitter streamNotifications(@PathVariable String especialidade) {
        counter.increment();
        String slug = normalizeToSlug(especialidade);
        
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        
        this.specialtyEmitters
            .computeIfAbsent(slug, k -> new CopyOnWriteArrayList<>())
            .add(emitter);

        emitter.onCompletion(() -> removeEmitter(slug, emitter));
        emitter.onTimeout(() -> removeEmitter(slug, emitter));
        emitter.onError((e) -> removeEmitter(slug, emitter));

        return emitter;
    }

    public void sendNotificationToClient(Notification notification, String slug) {
        List<SseEmitter> emitters = specialtyEmitters.get(slug);
        
        if (emitters != null && !emitters.isEmpty()) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(notification, MediaType.APPLICATION_JSON);
                } catch (IOException | IllegalStateException e) {
                    removeEmitter(slug, emitter);
                }
            }
        }
    }

    private void removeEmitter(String slug, SseEmitter emitter) {
        List<SseEmitter> emitters = specialtyEmitters.get(slug);
        if (emitters != null) {
            emitters.remove(emitter);
        }
    }

    @PostMapping("/send-test/{especialidade}")
    @Operation(summary = "Enviar notificação de teste")
    public ResponseEntity<String> sendTestNotification(@PathVariable String especialidade, @RequestBody Notification notification) {
        counter.increment();
        String slug = normalizeToSlug(especialidade);
        try {
            String routingKey = "atendimento." + slug;
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, notification);
            return ResponseEntity.ok("Notificação enviada com chave: " + routingKey);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }

    private String normalizeToSlug(String input) {
        if (input == null) return "geral";
        String normalized = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "")
                         .replaceAll("\\s+", "")
                         .replaceAll("[^a-zA-Z0-9]", "");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Notification Service operacional.");
    }
}