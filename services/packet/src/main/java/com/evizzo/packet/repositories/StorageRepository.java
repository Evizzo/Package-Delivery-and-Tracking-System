package com.evizzo.packet.repositories;

import com.evizzo.packet.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
