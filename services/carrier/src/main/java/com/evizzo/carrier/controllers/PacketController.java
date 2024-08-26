package com.evizzo.carrier.controllers;

import com.evizzo.carrier.dtos.PacketDTO;
import com.evizzo.carrier.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("carrier/packet")
public class PacketController {
    private final PacketService packetService;

    @GetMapping("/ready-for-pickup")
    public ResponseEntity<List<PacketDTO>> getPacketsReadyForPickup() {
        return new ResponseEntity<>(packetService.getAllPacketsOrderByCreatedAtDescReadyForPickup(), HttpStatus.OK);
    }
}
