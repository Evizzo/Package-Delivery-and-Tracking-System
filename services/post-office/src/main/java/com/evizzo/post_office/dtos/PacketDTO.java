package com.evizzo.post_office.dtos;

import com.evizzo.post_office.enums.PacketSize;
import com.evizzo.post_office.enums.PacketStatus;
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
    private PacketStatus packetStatus;
    private PacketSize packetSize;
    private Integer storedAtWarehouse;
}
