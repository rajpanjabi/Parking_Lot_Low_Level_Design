package domain;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Floor {
    private UUID id;
    private int floorNumber;
    private List<ParkingSlot> slots;

    public Floor(int floorNumber){
        this.id=UUID.randomUUID();
        this.floorNumber=floorNumber;
        this.slots=new ArrayList<>();
    }

    public UUID getId(){
        return this.id;
    }

    public int getFloorNumber(){
        return this.floorNumber;
    }

    public void addSlot(ParkingSlot slot){
        slots.add(slot);
    }

    public List<ParkingSlot> getSlot(){
        return this.slots;
    }

    public int getTotalSlots() {
        return slots.size();
    }
    public List<ParkingSlot> getAvailableSlots(Vehicle.VehicleType vehicleType){
        List<ParkingSlot> available= new ArrayList<>();
        for (ParkingSlot slot : slots){
            if (!slot.isOccupied() && slot.getSlotType()==vehicleType){
                available.add(slot);
            }
        }
        return available;
    }
}
