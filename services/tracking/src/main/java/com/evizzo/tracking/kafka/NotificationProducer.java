package com.evizzo.tracking.kafka;

import com.evizzo.tracking.dtos.NotificationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendPacketStatusUpdate(NotificationDTO notificationDTO) {
        try {
            String message = objectMapper.writeValueAsString(notificationDTO);
            kafkaTemplate.send("packet_status_updates", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing the update DTO", e);
        }
    }
}

