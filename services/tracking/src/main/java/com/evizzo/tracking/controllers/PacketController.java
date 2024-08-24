package com.evizzo.tracking.controllers;

import com.evizzo.tracking.dtos.PacketDTO;
import com.evizzo.tracking.enums.PacketStatus;
import com.evizzo.tracking.services.PacketService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("tracking/packet")
public class PacketController {
    private final PacketService packetService;

    @PostMapping
    public ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO) {
        return new ResponseEntity<>(packetService.savePacket(packetDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc() {
        List<PacketDTO> packets = packetService.getAllPacketsOrderByCreatedAtDesc();
        return new ResponseEntity<>(packets, HttpStatus.OK);
    }

    @PutMapping("/{trackingNumber}/update-status")
    @Transactional
    public ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status) {
        packetService.updatePacketStatus(trackingNumber, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
