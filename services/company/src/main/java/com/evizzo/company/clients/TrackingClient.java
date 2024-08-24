package com.evizzo.company.clients;

import com.evizzo.company.dtos.PacketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClient {
    @PostMapping("/tracking/packet")
    ResponseEntity<PacketDTO> createPacket(@RequestBody PacketDTO packetDTO);
}
