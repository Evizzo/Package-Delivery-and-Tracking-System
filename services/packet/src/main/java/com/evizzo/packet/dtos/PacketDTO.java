package com.evizzo.packet.dtos;

import com.evizzo.packet.enums.PacketSize;
import com.evizzo.packet.enums.PacketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacketDTO {
    private UUID trackingNumber;
    private String destinationAddress;
    private PacketStatus packetStatus;
    private PacketSize packetSize;
    private Integer storedAtWarehouse;
}
