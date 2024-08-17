package com.evizzo.post_office.repositories;

import com.evizzo.post_office.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PacketRepository extends JpaRepository<Packet, UUID> {
    List<Packet> findAllByOrderByCreatedAtDesc();
}
