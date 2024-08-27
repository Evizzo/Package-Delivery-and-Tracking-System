package com.evizzo.carrier.controllers;

import com.evizzo.carrier.dtos.PacketDTO;
import com.evizzo.carrier.enums.PacketStatus;
import com.evizzo.carrier.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("carrier/packet")
public class PacketController {
    private final PacketService packetService;

    @GetMapping("/ready-for-pickup")
    public ResponseEntity<List<PacketDTO>> getPacketsReadyForPickup() {
        return new ResponseEntity<>(packetService.getAllPacketsOrderByCreatedAtDescReadyForPickup(), HttpStatus.OK);
    }

    @PutMapping("/{trackingNumber}/status")
    public ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status) {
        packetService.updatePacketStatus(trackingNumber, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{trackingNumber}/pickup")
    public ResponseEntity<Void> pickupPacket(@PathVariable UUID trackingNumber) {
        packetService.pickupPacket(trackingNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
