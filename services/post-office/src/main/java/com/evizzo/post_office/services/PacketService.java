package com.evizzo.post_office.services;

import com.evizzo.post_office.dtos.PacketDTO;
import com.evizzo.post_office.entities.Packet;
import com.evizzo.post_office.enums.PacketStatus;
import com.evizzo.post_office.repositories.PacketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacketService {
    private final PacketRepository packetRepository;
    private final DTOService dtoService;

    public PacketDTO sendPackage(PacketDTO packetDTO) {
        Packet packet = dtoService.convertToEntity(packetDTO);
        packet.setPacketStatus(PacketStatus.READY_FOR_PICKUP);
        Packet savedPacket = packetRepository.save(packet);
        return dtoService.convertToDto(savedPacket);
    }
}
