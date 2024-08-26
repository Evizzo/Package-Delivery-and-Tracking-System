package com.evizzo.carrier.clients;

import com.evizzo.carrier.dtos.PacketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClient {
    @GetMapping("/tracking/packet/all")
    ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc();
}
