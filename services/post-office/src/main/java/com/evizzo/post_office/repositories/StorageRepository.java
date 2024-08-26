package com.evizzo.post_office.repositories;

import com.evizzo.post_office.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
