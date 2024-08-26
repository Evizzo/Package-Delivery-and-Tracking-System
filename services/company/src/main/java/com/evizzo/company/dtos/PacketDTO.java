package com.evizzo.company.dtos;

import com.evizzo.company.enums.PacketSize;
import com.evizzo.company.enums.PacketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacketDTO {
    private String destinationAddress;
    private PacketStatus packetStatus;
    private PacketSize packetSize;
}
