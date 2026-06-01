package com.healthsys.notification_service.consumer;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import com.healthsys.notification_service.controller.NotificationController;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.text.Normalizer;

@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationController notificationController;

    /**
     * Listener genérico usando o wildcard '#' para qualquer especialidade.
     * key = "atendimento.#" escuta tudo que começa com 'atendimento.'
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "queue.atendimento.todas", durable = "true"),
        exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = "atendimento.#"
    ))
    public void receiveMessage(Notification notification, 
                               @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        
        // Extrai o que vem depois de "atendimento." (ex: "cardiologia", "clinico", "ginecologiaeobstetricia")
        String especialidade = routingKey.replace("atendimento.", "");
        
        // Normaliza a especialidade para garantir o padrão slug
        String slug = normalizeToSlug(especialidade);
        
        log.info("Notificação recebida para a especialidade: {}", slug);
        notificationController.sendNotificationToClient(notification, slug);
    }

    private String normalizeToSlug(String input) {
        if (input == null) return "geral";
        // Remove acentos e espaços, converte para minúsculo
        String normalized = Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "")
                         .replaceAll("\\s+", "")
                         .replaceAll("[^a-zA-Z0-9]", "");
    }
}