package com.healthsys.notification_service.controller;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;
    private final Counter counter;

    // Mapa que separa os emissores por especialidade (Ex: "cardiologia" -> List de SseEmitters)
    private final Map<String, List<SseEmitter>> specialtyEmitters = new ConcurrentHashMap<>();

    public NotificationController(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.counter = Counter.builder("notification_service_requests_total")
                .description("Total de chamadas ao controller NotificationController")
                .tag("controller", "NotificationController")
                .tag("endpoint", "/api/notifications")
                .register(meterRegistry);
    }

    /**
     * O React se conecta passando a especialidade no path.
     * Exemplo: /api/notifications/stream/cardiologia
     */
    @GetMapping(value = "/stream/{especialidade}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNotifications(@PathVariable String especialidade) {
        counter.increment();
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        
        // Adiciona o médico à lista da especialidade dele
        this.specialtyEmitters
            .computeIfAbsent(especialidade.toLowerCase(), k -> new CopyOnWriteArrayList<>())
            .add(emitter);

        emitter.onCompletion(() -> removeEmitter(especialidade, emitter));
        emitter.onTimeout(() -> removeEmitter(especialidade, emitter));
        emitter.onError((e) -> removeEmitter(especialidade, emitter));

        return emitter;
    }

    /**
     * Método chamado pelo Consumer enviando para a especialidade correta.
     */
    public void sendNotificationToClient(Notification notification, String especialidade) {
        List<SseEmitter> emitters = specialtyEmitters.get(especialidade.toLowerCase());
        
        if (emitters != null) {
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(notification, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    removeEmitter(especialidade, emitter);
                }
            }
        }
    }

    private void removeEmitter(String especialidade, SseEmitter emitter) {
        List<SseEmitter> emitters = specialtyEmitters.get(especialidade.toLowerCase());
        if (emitters != null) {
            emitters.remove(emitter);
        }
    }

    @PostMapping("/send-test/{especialidade}")
    public ResponseEntity<String> sendTestNotification(@PathVariable String especialidade, @RequestBody Notification notification) {
        counter.increment();
        try {
            String routingKey = "atendimento." + especialidade.toLowerCase();
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, notification);
            return ResponseEntity.ok("Notificação enviada para a Exchange com chave: " + routingKey);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        counter.increment();
        return ResponseEntity.ok("Notification Service operacional (Topic Routing Mode).");
    }
}