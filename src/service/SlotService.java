package service;

import java.util.Optional;
import java.util.UUID;

import domain.ParkingSlot;
import domain.Vehicle;
import repository.SlotRepository;

public class SlotService {
    // Create a slot
    // getAvailableSlots(VehicleType)
    // allocateSlot
    // releaseSlot
    // depends on SlotRepository
    SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository){
        this.slotRepository=slotRepository;
    }

    // Create a slot
    public ParkingSlot createSlot(Vehicle.VehicleType slotType, int floorNumber){
        System.out.println("[Service] creating a new slot of type: "+slotType +" on floor: "+floorNumber);
        ParkingSlot slot = new ParkingSlot(slotType, floorNumber);
        // Save to repo
        slotRepository.save(slot);
        return slot;
    }

    // Allocate a slot, we use optional as return type, because there may be case where there are no slots available for this vehicle type
    public Optional<ParkingSlot> allocateSlot(Vehicle.VehicleType vehicleType){
        System.out.println("[SERVICE] Allocating slot for vehicle type: " + vehicleType);
        Optional<ParkingSlot> slot =slotRepository.allocateSlot(vehicleType);
        if (slot.isPresent()) {
            System.out.println("[SERVICE] Slot allocated successfully: " + slot.get().getId());
        } else {
            System.out.println("[SERVICE] No available slots for vehicle type: " + vehicleType);
        }
        return slot;

    }

    // Release a slot
    public void releaseSlot(UUID slotId){
        System.out.println("[SERVICE] Releasing slot: " + slotId);
        slotRepository.releaseSlot(slotId);
        System.out.println("[SERVICE] Slot released successfully: " + slotId);
    }

    public long getAvailableSlotsCount(Vehicle.VehicleType vehicleType){
        return slotRepository.getAvailableSlots(vehicleType).size();
    }
    
}
