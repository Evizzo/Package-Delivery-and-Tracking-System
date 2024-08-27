package com.evizzo.post_office.services;

import com.evizzo.post_office.clients.TrackingClient;
import com.evizzo.post_office.dtos.PacketDTO;
import com.evizzo.post_office.enums.PacketStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PacketService {
    private final TrackingClient trackingClient;
    private final StorageService storageService;

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

    public void pickupPacket(UUID trackingNumber) {
        ResponseEntity<Optional<PacketDTO>> response = trackingClient.findPacketById(trackingNumber);

        Optional<PacketDTO> optionalPacketDTO = response.getBody();

        if (Objects.requireNonNull(optionalPacketDTO).isPresent()) {
            PacketDTO packetDTO = optionalPacketDTO.get();

            if (packetDTO.getPacketStatus() == PacketStatus.READY_FOR_PICKUP) {
                packetDTO.setPacketStatus(PacketStatus.PICKED_UP);

                storageService.removePacket(packetDTO.getPacketSize());

                trackingClient.updatePacketStatus(trackingNumber, PacketStatus.PICKED_UP);
            } else {
                throw new IllegalStateException("Packet is not ready for pickup.");
            }
        } else {
            throw new IllegalArgumentException("Packet not found for tracking number: " + trackingNumber);
        }
    }
}
