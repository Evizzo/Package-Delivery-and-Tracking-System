package com.evizzo.post_office.controllers;

import com.evizzo.post_office.services.PacketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("post-office/packet")
public class PacketController {
    private final PacketService packetService;

}
