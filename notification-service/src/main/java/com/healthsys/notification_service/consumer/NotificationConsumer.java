package com.healthsys.notification_service.consumer;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import com.healthsys.notification_service.controller.NotificationController;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationController notificationController;

    /**
     * Listener para Cardiologia.
     * Cria a fila automaticamente e a vincula à Routing Key 'atendimento.cardiologia'
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "queue.atendimento.cardiologia", durable = "true"),
        exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = "atendimento.cardiologia"
    ))
    public void receiveCardioMessage(Notification notification) {
        processAndSend(notification, "cardiologia");
    }

    /**
     * Listener para Clinico Geral.
     * Cria a fila e vincula à Routing Key 'atendimento.clinico'
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "queue.atendimento.clinico", durable = "true"),
        exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
        key = "atendimento.clinico"
    ))
    public void receiveClinicoMessage(Notification notification) {
        processAndSend(notification, "clinico");
    }

    /**
     * Método auxiliar para evitar repetição de código (DRY)
     */
    private void processAndSend(Notification notification, String especialidade) {
        log.info("Notificação de {} recebida para: {}", especialidade, notification.getRecipient());
        
        System.out.println("=== NOTIFICAÇÃO " + especialidade.toUpperCase() + " ===");
        System.out.println("Paciente: " + notification.getRecipient());
        System.out.println("Alerta: " + notification.getMessage());

        // Envia para o front-end filtrando pela especialidade
        notificationController.sendNotificationToClient(notification, especialidade);
    }
}