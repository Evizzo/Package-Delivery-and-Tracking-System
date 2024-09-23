package com.evizzo.notification.kafka;

import com.evizzo.notification.entities.Notification;
import com.evizzo.notification.services.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "packet_status_updates", groupId = "notification-group")
    public void consumePacketStatusUpdate(String message) {
        try {
            Notification notification = objectMapper.readValue(message, Notification.class);
            notificationService.createNotification(notification);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the Kafka message", e);
        }
    }
}
