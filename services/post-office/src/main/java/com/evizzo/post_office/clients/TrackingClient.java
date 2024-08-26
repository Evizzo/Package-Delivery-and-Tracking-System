package com.evizzo.post_office.clients;

import com.evizzo.post_office.dtos.PacketDTO;
import com.evizzo.post_office.enums.PacketStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClient {
    @PutMapping("/tracking/packet/{trackingNumber}/update-status")
    ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status);

    @PutMapping("/tracking/packet/send-packet")
    ResponseEntity<Void> sendPacket(@RequestBody PacketDTO sendPacket);

    @GetMapping("/tracking/packet/{trackingNumber}/track")
    ResponseEntity<Optional<PacketDTO>> findPacketById(@PathVariable UUID trackingNumber);
}
