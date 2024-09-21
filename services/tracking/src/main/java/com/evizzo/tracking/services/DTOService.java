package com.evizzo.tracking.services;

import com.evizzo.tracking.dtos.PacketDTO;
import com.evizzo.tracking.entities.Packet;
import org.springframework.stereotype.Service;

@Service
public class DTOService {
    public PacketDTO convertToDto(Packet packet) {
        return PacketDTO.builder()
                .trackingNumber(packet.getTrackingNumber())
                .sendToPersonUsername(packet.getSendToPersonUsername())
                .destinationAddress(packet.getDestinationAddress())
                .packetStatus(packet.getPacketStatus())
                .createdAt(packet.getCreatedAt())
                .lastModifiedDate(packet.getLastModifiedDate())
                .packetSize(packet.getPacketSize())
                .storedAtWarehouse(packet.getStoredAtWarehouse())
                .build();
    }

    public Packet convertToEntity(PacketDTO packetDTO) {
        return Packet.builder()
                .trackingNumber(packetDTO.getTrackingNumber())
                .sendToPersonUsername(packetDTO.getSendToPersonUsername())
                .destinationAddress(packetDTO.getDestinationAddress())
                .packetStatus(packetDTO.getPacketStatus())
                .createdAt(packetDTO.getCreatedAt())
                .lastModifiedDate(packetDTO.getLastModifiedDate())
                .packetSize(packetDTO.getPacketSize())
                .storedAtWarehouse(packetDTO.getStoredAtWarehouse())
                .build();
    }
}
