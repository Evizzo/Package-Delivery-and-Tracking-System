package com.evizzo.packet.kafka;

import com.evizzo.packet.dtos.NotificationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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

