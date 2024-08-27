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

    /**
     * Sends a packet identified by its tracking number.
     * Updates the packet status to "READY_FOR_PICKUP" and stores it in the warehouse.
     *
     * @param trackingNumber the UUID of the packet to send.
     * @return ResponseEntity with an HTTP status of OK if the packet was successfully sent.
     */
    @PutMapping("/{trackingNumber}/send")
    public ResponseEntity<Void> sendPacket(@PathVariable UUID trackingNumber) {
        packetService.sendPacket(trackingNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Marks a packet as picked up, identified by its tracking number.
     * Updates the packet status to "PICKED_UP" and removes it from the warehouse storage.
     *
     * @param trackingNumber the UUID of the packet to mark as picked up.
     * @return ResponseEntity with an HTTP status of OK if the packet was successfully picked up.
     */
    @PutMapping("/{trackingNumber}/pickup")
    public ResponseEntity<Void> pickupPacket(@PathVariable UUID trackingNumber) {
        packetService.pickupPacket(trackingNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
