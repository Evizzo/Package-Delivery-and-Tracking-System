package com.evizzo.carrier.clients;

import com.evizzo.carrier.auth.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "POST-OFFICE-SERVICE", configuration = FeignClientConfig.class)
public interface PostOfficeClient {
    @PutMapping("/post-office/packet/{trackingNumber}/pickup")
    ResponseEntity<Void> pickupPacket(@PathVariable UUID trackingNumber);
}
