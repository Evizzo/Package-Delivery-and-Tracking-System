package com.evizzo.carrier.clients;

import com.evizzo.carrier.dtos.PacketDTO;
import com.evizzo.carrier.enums.PacketStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClient {
    @GetMapping("/tracking/packet/all")
    ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc();

    @PutMapping("/tracking/packet/{trackingNumber}/update-status")
    ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status);

}
