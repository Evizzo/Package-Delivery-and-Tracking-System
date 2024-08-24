package com.evizzo.tracking.repositories;

import com.evizzo.tracking.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PacketRepository extends JpaRepository<Packet, UUID> {
    List<Packet> findAllByOrderByCreatedAtDesc();
}
