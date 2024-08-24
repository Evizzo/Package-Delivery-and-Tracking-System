package com.evizzo.company.services;

import com.evizzo.company.clients.TrackingClient;
import com.evizzo.company.dtos.PacketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacketService {
    private final TrackingClient trackingClient;

    public PacketDTO createPacket(PacketDTO packetDTO) {
        return trackingClient.createPacket(packetDTO).getBody();
    }
}
