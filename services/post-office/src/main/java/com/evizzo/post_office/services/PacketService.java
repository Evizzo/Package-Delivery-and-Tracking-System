package com.evizzo.post_office.services;

import com.evizzo.post_office.clients.TrackingClient;
import com.evizzo.post_office.enums.PacketStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PacketService {
    private final TrackingClient trackingClient;

    public void sendPacket(UUID trackingNumber) {
        trackingClient.updatePacketStatus(trackingNumber, PacketStatus.READY_FOR_PICKUP);
    }
}
