package domain;
import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private Vehicle.VehicleType slotType;
    private int floorNumber;
    private boolean isOccupied;

    public ParkingSlot(Vehicle.VehicleType slotType, int floorNumber ){
        this.id=UUID.randomUUID();
        this.slotType=slotType;
        this.floorNumber=floorNumber;
        this.isOccupied=false;
    }
    public UUID getId(){
        return this.id;
    }

    public Vehicle.VehicleType getSlotType(){
        return this.slotType;
    }

    public int getFloorNumber(){
        return this.floorNumber;
    }

    public boolean isOccupied(){
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied){
        this.isOccupied=occupied;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotType=" + slotType +
                ", isOccupied=" + isOccupied +
                ", floorNumber=" + floorNumber +
                '}';
    }

    
}
