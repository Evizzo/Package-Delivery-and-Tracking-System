package com.evizzo.packet.clients;

import com.evizzo.packet.auth.FeignClientConfig;
import com.evizzo.packet.dtos.PacketDTO;
import com.evizzo.packet.enums.PacketStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "TRACKING-SERVICE", configuration = FeignClientConfig.class)
public interface TrackingClient {
    @PostMapping("/tracking/packet")
    ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO);

    @GetMapping("/tracking/packet/all")
    ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc();

    @PutMapping("/tracking/packet/send-packet")
    ResponseEntity<Void> sendPacket(@RequestBody PacketDTO sendPacket);

    @GetMapping("/tracking/packet/{trackingNumber}/track")
    ResponseEntity<Optional<PacketDTO>> findPacketById(@PathVariable UUID trackingNumber);

    @PutMapping("/tracking/packet/{trackingNumber}/update-status")
    ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status);
}
