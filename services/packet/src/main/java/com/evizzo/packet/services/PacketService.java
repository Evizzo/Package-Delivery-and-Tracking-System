package com.evizzo.packet.services;

import com.evizzo.packet.clients.TrackingClient;
import com.evizzo.packet.dtos.NotificationDTO;
import com.evizzo.packet.dtos.PacketDTO;
import com.evizzo.packet.enums.PacketStatus;
import com.evizzo.packet.kafka.NotificationProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacketService {
    private final TrackingClient trackingClient;
    private final StorageService storageService;
    private final NotificationProducer notificationProducer;

    public PacketDTO createPacket(PacketDTO packetDTO) {
        return trackingClient.createPacket(packetDTO).getBody();
    }

    public void sendPacket(UUID trackingNumber) {
        ResponseEntity<Optional<PacketDTO>> response = trackingClient.findPacketById(trackingNumber);

        Optional<PacketDTO> optionalPacketDTO = response.getBody();

        if (Objects.requireNonNull(optionalPacketDTO).isPresent()) {
            PacketDTO packetDTO = optionalPacketDTO.get();

            packetDTO.setTrackingNumber(trackingNumber);
            packetDTO.setPacketStatus(PacketStatus.READY_FOR_PICKUP);
            packetDTO.setStoredAtWarehouse(storageService.addPacket(packetDTO.getPacketSize()));

            trackingClient.sendPacket(packetDTO);
        } else {
            throw new IllegalArgumentException("Packet not found for tracking number: " + trackingNumber);
        }
    }

    public List<PacketDTO> getAllPacketsOrderByCreatedAtDescToBeSend() {
        List<PacketDTO> packets = trackingClient.getAllPacketsOrderByCreatedAtDesc().getBody();

        if (packets == null) {
            return List.of();
        }

        return packets.stream()
                .filter(packet -> packet.getPacketStatus() == PacketStatus.TO_BE_SENT)
                .collect(Collectors.toList());
    }

    public void pickupPacket(UUID trackingNumber) {
        ResponseEntity<Optional<PacketDTO>> response = trackingClient.findPacketById(trackingNumber);

        Optional<PacketDTO> optionalPacketDTO = response.getBody();

        if (Objects.requireNonNull(optionalPacketDTO).isPresent()) {
            PacketDTO packetDTO = optionalPacketDTO.get();

            if (packetDTO.getPacketStatus() == PacketStatus.READY_FOR_PICKUP) {
                packetDTO.setPacketStatus(PacketStatus.PICKED_UP);

                storageService.removePacket(packetDTO.getPacketSize());

                trackingClient.updatePacketStatus(trackingNumber, PacketStatus.PICKED_UP);

                NotificationDTO updateDTO = new NotificationDTO(
                        packetDTO.getTrackingNumber(),
                        packetDTO.getSendToPersonUsername(),
                        "Packet is picked up by carrier!"
                );

                notificationProducer.sendPacketStatusUpdate(updateDTO);
            } else {
                throw new IllegalStateException("Packet is not ready for pickup.");
            }
        } else {
            throw new IllegalArgumentException("Packet not found for tracking number: " + trackingNumber);
        }
    }

    public List<PacketDTO> getAllPacketsOrderByCreatedAtDescReadyForPickup() {
        List<PacketDTO> packets = trackingClient.getAllPacketsOrderByCreatedAtDesc().getBody();

        if (packets == null) {
            return List.of();
        }

        return packets.stream()
                .filter(packet -> packet.getPacketStatus() == PacketStatus.READY_FOR_PICKUP)
                .collect(Collectors.toList());
    }
}
