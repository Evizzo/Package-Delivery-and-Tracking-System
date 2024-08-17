package com.evizzo.post_office.controllers;

import com.evizzo.post_office.dtos.PacketDTO;
import com.evizzo.post_office.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("post-office/packet")
public class PacketController {
    private final PacketService packetService;

    @PostMapping("/send")
    public ResponseEntity<PacketDTO> sendPackage(@RequestBody PacketDTO packetDTO) {
        return new ResponseEntity<>(packetService.sendPackage(packetDTO), HttpStatus.CREATED);
    }
}
