package com.evizzo.company.dtos;

import com.evizzo.company.enums.PacketStatus;
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
    private String destinationAddress;
    private PacketStatus packetStatus;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
}
