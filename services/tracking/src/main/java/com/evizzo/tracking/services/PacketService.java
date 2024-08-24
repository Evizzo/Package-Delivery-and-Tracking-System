package com.evizzo.tracking.services;

import com.evizzo.tracking.dtos.PacketDTO;
import com.evizzo.tracking.entities.Packet;
import com.evizzo.tracking.enums.PacketStatus;
import com.evizzo.tracking.repositories.PacketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacketService {
    private final PacketRepository packetRepository;
    private final DTOService dtoService;

    public PacketDTO savePacket(PacketDTO packetDTO) {
        Packet packet = dtoService.convertToEntity(packetDTO);
        packet.setPacketStatus(PacketStatus.TO_BE_SENT);
        Packet savedPacket = packetRepository.save(packet);
        return dtoService.convertToDto(savedPacket);
    }

    public List<PacketDTO> getAllPacketsOrderByCreatedAtDesc() {
        List<Packet> packets = packetRepository.findAllByOrderByCreatedAtDesc();
        return packets.stream()
                .map(dtoService::convertToDto)
                .collect(Collectors.toList());
    }
}
