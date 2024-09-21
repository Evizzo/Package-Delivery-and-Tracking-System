package com.evizzo.tracking.dtos;

import com.evizzo.tracking.enums.PacketSize;
import com.evizzo.tracking.enums.PacketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacketDTO {
    private UUID trackingNumber;
    private String sendToPersonUsername;
    private String destinationAddress;
    private PacketStatus packetStatus;
    private Integer storedAtWarehouse;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
    private PacketSize packetSize;
}
