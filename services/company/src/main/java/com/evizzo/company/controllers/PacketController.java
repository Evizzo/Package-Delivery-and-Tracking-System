package com.evizzo.company.controllers;

import com.evizzo.company.dtos.PacketDTO;
import com.evizzo.company.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("company/packet")
public class PacketController {
    private final PacketService packetService;

    /**
     * Creates a new packet with the provided details.
     *
     * @param packetDTO the details of the packet to create.
     * @return ResponseEntity containing the created {@link PacketDTO} object and an HTTP status of CREATED.
     */
    @PostMapping
    public ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO) {
        return new ResponseEntity<>(packetService.createPacket(packetDTO), HttpStatus.CREATED);
    }

    /**
     * Sends a packet identified by its tracking number.
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
     * Retrieves a list of packets that are to be sent, sorted by their creation date in descending order.
     *
     * @return ResponseEntity containing the list of {@link PacketDTO} objects and an HTTP status of OK.
     */
    @GetMapping("/to-be-sent")
    public ResponseEntity<List<PacketDTO>> getPacketsToBeSent() {
        return new ResponseEntity<>(packetService.getAllPacketsOrderByCreatedAtDescToBeSend(), HttpStatus.OK);
    }
}
