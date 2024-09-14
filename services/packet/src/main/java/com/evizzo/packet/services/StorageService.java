package com.evizzo.packet.services;

import com.evizzo.packet.entities.Storage;
import com.evizzo.packet.enums.PacketSize;
import com.evizzo.packet.repositories.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
