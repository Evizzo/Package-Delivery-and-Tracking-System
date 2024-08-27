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

    /**
     * Retrieves a list of packets that are ready for pickup, sorted by their creation date in descending order.
     *
     * @return ResponseEntity containing the list of {@link PacketDTO} objects and an HTTP status of OK.
     */
    @GetMapping("/ready-for-pickup")
    public ResponseEntity<List<PacketDTO>> getPacketsReadyForPickup() {
        return new ResponseEntity<>(packetService.getAllPacketsOrderByCreatedAtDescReadyForPickup(), HttpStatus.OK);
    }

    /**
     * Updates the status of a packet identified by its tracking number.
     *
     * @param trackingNumber the UUID of the packet to update.
     * @param status the new {@link PacketStatus} to set for the packet.
     * @return ResponseEntity with an HTTP status of NO_CONTENT if the update was successful.
     */
    @PutMapping("/{trackingNumber}/status")
    public ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status) {
        packetService.updatePacketStatus(trackingNumber, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Marks a packet as picked up, identified by its tracking number.
     *
     * @param trackingNumber the UUID of the packet to mark as picked up.
     * @return ResponseEntity with an HTTP status of NO_CONTENT if the operation was successful.
     */
    @PutMapping("/{trackingNumber}/pickup")
    public ResponseEntity<Void> pickupPacket(@PathVariable UUID trackingNumber) {
        packetService.pickupPacket(trackingNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
