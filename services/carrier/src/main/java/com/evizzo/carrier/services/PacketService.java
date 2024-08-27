package com.evizzo.carrier.services;

import com.evizzo.carrier.clients.PostOfficeClient;
import com.evizzo.carrier.clients.TrackingClient;
import com.evizzo.carrier.dtos.PacketDTO;
import com.evizzo.carrier.enums.PacketStatus;
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

    public List<PacketDTO> getAllPacketsOrderByCreatedAtDescReadyForPickup() {
        List<PacketDTO> packets = trackingClient.getAllPacketsOrderByCreatedAtDesc().getBody();

        if (packets == null) {
            return List.of();
        }

        return packets.stream()
                .filter(packet -> packet.getPacketStatus() == PacketStatus.READY_FOR_PICKUP)
                .collect(Collectors.toList());
    }

    public void updatePacketStatus(UUID trackingNumber, PacketStatus status) {
        trackingClient.updatePacketStatus(trackingNumber, status);
    }

    public void pickupPacket(UUID trackingNumber){
        postOfficeClient.pickupPacket(trackingNumber);
    }
}
