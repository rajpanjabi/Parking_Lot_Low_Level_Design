package domain;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
   
    private UUID id;
    private UUID vehicleId;
    private UUID slotId;
    private LocalDateTime entryTime;
    private boolean isActive;

    public Ticket(UUID vehicleId, UUID slotId){
        this.id=UUID.randomUUID();
        this.vehicleId=vehicleId;
        this.slotId=slotId;
        this.entryTime=LocalDateTime.now();
        this.isActive=true;
    }
    public UUID getId(){
        return this.id;
    }

    public UUID getVehicleId(){
        return this.vehicleId;
    }

    public UUID getSlotId(){
        return this.slotId;
    }

    public LocalDateTime getEntryTime(){
        return this.entryTime;
    }

    public void deactivate(){
        this.isActive=false;
    }

    public boolean isActive(){
        return this.isActive;
    }

}

