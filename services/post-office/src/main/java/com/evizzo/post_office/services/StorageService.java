package com.evizzo.post_office.services;

import com.evizzo.post_office.enums.PacketSize;
import com.evizzo.post_office.entities.Storage;
import com.evizzo.post_office.repositories.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    private List<Storage> getAllStorages() {
        return storageRepository.findAll();
    }

    @Transactional
    public boolean hasSpace(PacketSize packetSize) {
        for (Storage storage : getAllStorages()) {
            if (storage.getCurrentLoad() + packetSize.getSize() <= storage.getTotalCapacity()) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Integer addPacket(PacketSize packetSize) {
        for (Storage storage : getAllStorages()) {
            if (storage.getCurrentLoad() + packetSize.getSize() <= storage.getTotalCapacity()) {
                storage.setCurrentLoad(storage.getCurrentLoad() + packetSize.getSize());
                storageRepository.save(storage);
                return storage.getId();
            }
        }
        throw  new IllegalStateException("No available space in any storage for this packet size.");
    }

    @Transactional
    public void removePacket(PacketSize packetSize) {
        for (Storage storage : getAllStorages()) {
            if (storage.getCurrentLoad() >= packetSize.getSize()) {
                storage.setCurrentLoad(storage.getCurrentLoad() - packetSize.getSize());
                storageRepository.save(storage);
                return;
            }
        }
        throw new IllegalStateException("Invalid operation. Current load is less than the packet size.");
    }

    public int getAvailableSpace() {
        return getAllStorages().stream()
                .mapToInt(storage -> storage.getTotalCapacity() - storage.getCurrentLoad())
                .sum();
    }
}
