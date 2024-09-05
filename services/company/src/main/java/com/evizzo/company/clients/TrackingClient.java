package com.evizzo.company.clients;

import com.evizzo.company.auth.FeignClientConfig;
import com.evizzo.company.dtos.PacketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "TRACKING-SERVICE", configuration = FeignClientConfig.class)
public interface TrackingClient {
    @PostMapping("/tracking/packet")
    ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO);

    @GetMapping("/tracking/packet/all")
    ResponseEntity<List<PacketDTO>> getAllPacketsOrderByCreatedAtDesc();
}
