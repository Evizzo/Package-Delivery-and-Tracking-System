package com.evizzo.tracking.controllers;

import com.evizzo.tracking.dtos.PacketDTO;
import com.evizzo.tracking.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
