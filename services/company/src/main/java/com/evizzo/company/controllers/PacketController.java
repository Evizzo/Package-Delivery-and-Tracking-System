package com.evizzo.company.controllers;

import com.evizzo.company.dtos.PacketDTO;
import com.evizzo.company.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("company/packet")
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

    @PostMapping("/send")
    public ResponseEntity<PacketDTO> sendPacket(@RequestBody PacketDTO packetDTO) {
        packetService.sendPacket(packetDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
