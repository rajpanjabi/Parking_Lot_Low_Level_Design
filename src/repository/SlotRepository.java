package repository;

import domain.ParkingSlot;
import domain.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SlotRepository {
    // save, release, allocateSlot, getAvailableSlots
    // To store slot we use a Map<Id, Slot> . We use concurrent hashmap for making sure concurrent access is handled.
    Map<UUID,ParkingSlot> slots = new ConcurrentHashMap<>();
    
    public ParkingSlot save(ParkingSlot slot){
        slots.put(slot.getId(), slot);
        return slot;
    }

    public void releaseSlot(UUID slotId){
        slots.computeIfPresent(slotId, (id, slot)->{
            slot.setOccupied(false);
            return slot;
        });
    }

    public Optional<ParkingSlot> allocateSlot(Vehicle.VehicleType vehicleType){
        // we use stream to find the first available aprking slot and then mark it as occupied
        return slots.values().stream()
        .filter(slot->slot.getSlotType()==vehicleType && !slot.isOccupied())
        .findFirst()
        .map(slot -> {
            slot.setOccupied(true); 
            return slot;
            });
    }
    public List<ParkingSlot> getAvailableSlots(Vehicle.VehicleType vehicleType){
        return slots.values().stream()
        .filter(slot -> slot.getSlotType()==vehicleType && !slot.isOccupied())
        .toList();
    }

}