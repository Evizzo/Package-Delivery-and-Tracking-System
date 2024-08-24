package com.evizzo.company.services;

import com.evizzo.company.clients.PostOfficeClient;
import com.evizzo.company.clients.TrackingClient;
import com.evizzo.company.dtos.PacketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
