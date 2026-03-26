package controller;

import domain.Ticket;
import domain.Vehicle;
import java.util.Optional;
import java.util.UUID;
import service.SlotService;
import service.TicketService;

public class EntryController {
    // Dependencies
    TicketService ticketService;
    SlotService slotService;

    // Constructor
    public EntryController(TicketService ticketService, SlotService slotService ){
        this.ticketService=ticketService;
        this.slotService=slotService;
    }
    
    public EntryResponse enterVehicle(String licensePlate, Vehicle.VehicleType vehicleType){
        // Create a vehicle
        // Check if parking spot available for the vehicle type
        // Generate a ticket
        // Save ticket
        // Mark Slot occupied

        System.out.println("[CONTROLLER] Vehicle entry request - License: " + licensePlate + ", Type: " + vehicleType);
        try{
        // Creating Vehicle
        Vehicle vehicle = new Vehicle(licensePlate, vehicleType);
        System.out.println("[CONTROLLER] Vehicle created: " + vehicle.getId());

        // Check if parking spot available for the vehicle type
        Optional<UUID> slotId=slotService.allocateSlot(vehicleType).map(slot -> slot.getId());;

        if (slotId.isEmpty()){
            return new EntryResponse(false, null, null, licensePlate);
        }
        
        // Generate a ticket
        Ticket ticket = ticketService.generateTicket(vehicle, slotId.get());

        System.out.println("[CONTROLLER] Vehicle entry successful - Ticket: " + ticket.getId() + ", Slot: " + slotId.get());
        return new EntryResponse(true, ticket.getId(), slotId.get(), "Entry successful");

        }catch(Exception e){
            System.out.println("[CONTROLLER] Vehicle entry failed: " + e.getMessage());
            return new EntryResponse(false, null, null, e.getMessage());
        }
    }

    // DTO for the response of enterVehicle, created using static inner class (belongs to class, not the object)
    public static class EntryResponse{
        private final boolean success;
        private final UUID ticketId;
        private final UUID slotId;
        private final String message;

        public EntryResponse(boolean success, UUID ticketId,UUID slotId, String message ){
            this.success=success;
            this.ticketId=ticketId;
            this.slotId=slotId;
            this.message=message;
        }

        public boolean isSuccess() { return success; }
        public UUID getTicketId() { return ticketId; }
        public UUID getSlotId() { return slotId; }
        public String getMessage() { return message; }
    }
}
