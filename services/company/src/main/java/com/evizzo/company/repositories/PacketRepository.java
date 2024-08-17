package com.evizzo.company.repositories;

import com.evizzo.company.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacketRepository extends JpaRepository<Packet, UUID> {
}
