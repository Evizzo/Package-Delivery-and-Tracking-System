package com.evizzo.company.controllers;

import com.evizzo.company.dtos.PacketDTO;
import com.evizzo.company.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("company/packet")
public class PacketController {
    private final PacketService packetService;

    @PostMapping
    public ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO) {
        return new ResponseEntity<>(packetService.createPacket(packetDTO), HttpStatus.CREATED);
    }
}
