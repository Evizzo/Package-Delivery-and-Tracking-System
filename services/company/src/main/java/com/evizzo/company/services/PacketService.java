package com.evizzo.company.services;

import com.evizzo.company.clients.PostOfficeClient;
import com.evizzo.company.clients.TrackingClient;
import com.evizzo.company.dtos.PacketDTO;
import com.evizzo.company.enums.PacketStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacketService {
    private final TrackingClient trackingClient;
    private final PostOfficeClient postOfficeClient;

    public PacketDTO createPacket(PacketDTO packetDTO) {
        return trackingClient.createPacket(packetDTO).getBody();
    }

    public void sendPacket(UUID trackingNumber) {
        postOfficeClient.sendPacket(trackingNumber);
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
}
