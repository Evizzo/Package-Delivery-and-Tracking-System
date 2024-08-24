package com.evizzo.post_office.controllers;

import com.evizzo.post_office.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("post-office/packet")
public class PacketController {
    private final PacketService packetService;

    @PutMapping("/{trackingNumber}/send")
    public ResponseEntity<Void> sendPacket(@PathVariable UUID trackingNumber) {
        packetService.sendPacket(trackingNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
