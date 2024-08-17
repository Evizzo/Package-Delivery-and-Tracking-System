package com.evizzo.company.services;

import com.evizzo.company.dtos.PacketDTO;
import com.evizzo.company.entities.Packet;
import com.evizzo.company.enums.PacketStatus;
import com.evizzo.company.repositories.PacketRepository;
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
