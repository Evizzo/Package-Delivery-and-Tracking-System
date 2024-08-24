package com.evizzo.post_office.clients;

import com.evizzo.post_office.enums.PacketStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClient {
    @PutMapping("/tracking/packet/{trackingNumber}/update-status")
    ResponseEntity<Void> updatePacketStatus(@PathVariable UUID trackingNumber, @RequestParam PacketStatus status);
}
