package com.healthsys.notification_service.consumer;

import com.healthsys.notification_service.model.Notification;
import com.healthsys.notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Notification notification) {
        System.out.println("Notificação recebida com sucesso!");
        System.out.println("Paciente: " + notification.getRecipient());
        System.out.println("Alerta: " + notification.getMessage());
    }
}