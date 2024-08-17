package com.evizzo.company.entities;

import com.evizzo.company.enums.PacketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trackingNumber;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Enumerated(EnumType.STRING)
    private PacketStatus packetStatus;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
