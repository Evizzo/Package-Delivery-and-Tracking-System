package com.evizzo.post_office.services;

import com.evizzo.post_office.dtos.PacketDTO;
import com.evizzo.post_office.entities.Packet;
import org.springframework.stereotype.Service;

@Service
public class DTOService {
    public PacketDTO convertToDto(Packet packet) {
        return PacketDTO.builder()
                .trackingNumber(packet.getTrackingNumber())
                .destinationAddress(packet.getDestinationAddress())
                .packetStatus(packet.getPacketStatus())
                .createdAt(packet.getCreatedAt())
                .lastModifiedDate(packet.getLastModifiedDate())
                .build();
    }

    public Packet convertToEntity(PacketDTO packetDTO) {
        return Packet.builder()
                .trackingNumber(packetDTO.getTrackingNumber())
                .destinationAddress(packetDTO.getDestinationAddress())
                .packetStatus(packetDTO.getPacketStatus())
                .createdAt(packetDTO.getCreatedAt())
                .lastModifiedDate(packetDTO.getLastModifiedDate())
                .build();
    }
}
