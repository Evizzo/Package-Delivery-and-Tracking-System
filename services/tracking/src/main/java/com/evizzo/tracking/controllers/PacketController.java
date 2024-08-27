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
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("tracking/packet")
public class PacketController {
    private final PacketService packetService;

    /**
     * Creates a new packet with the provided details and sets its status to "TO_BE_SENT".
     *
     * @param packetDTO the details of the packet to create.
     * @return ResponseEntity containing the created {@link PacketDTO} object and an HTTP status of CREATED.
     */
    @PostMapping
    public ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO) {
        return new ResponseEntity<>(packetService.savePacket(packetDTO), HttpStatus.CREATED);
    }

    /**
     * Retrieves all packets ordered by their creation date in descending order.
     *
     * @return ResponseEntity containing the list of {@link PacketDTO} objects and an HTTP status of OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc() {
        List<PacketDTO> packets = packetService.getAllPacketsOrderByCreatedAtDesc();
        return new ResponseEntity<>(packets, HttpStatus.OK);
    }

    /**
     * Updates the status of a packet identified by its tracking number.
     * This operation is transactional.
     *
     * @param trackingNumber the UUID of the packet to update.
     * @param status the new {@link PacketStatus} to set for the packet.
     * @return ResponseEntity with an HTTP status of OK if the status was successfully updated.
     */
    @PutMapping("/{trackingNumber}/update-status")
    @Transactional
    public ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status) {
        packetService.updatePacketStatus(trackingNumber, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves the packet details by its tracking number.
     *
     * @param trackingNumber the UUID of the packet to retrieve.
     * @return ResponseEntity containing an {@link Optional} of {@link PacketDTO} and an HTTP status of OK.
     */
    @GetMapping("/{trackingNumber}/track")
    public ResponseEntity<Optional<PacketDTO>> findPacketById(@PathVariable UUID trackingNumber) {
        return new ResponseEntity<>(packetService.findPacketById(trackingNumber), HttpStatus.OK);
    }

    /**
     * Sends a packet with the provided details.
     * This operation is transactional.
     *
     * @param sendPacket the {@link PacketDTO} containing details of the packet to send.
     * @return ResponseEntity with an HTTP status of OK if the packet was successfully sent.
     */
    @PutMapping("/send-packet")
    @Transactional
    public ResponseEntity<Void> sendPacket(@RequestBody PacketDTO sendPacket) {
        packetService.sendPacket(sendPacket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
