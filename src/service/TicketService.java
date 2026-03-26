package service;

import domain.Ticket;
import domain.Vehicle;
import java.util.Optional;
import java.util.UUID;
import repository.TicketRepository;

public class TicketService {
    // dependency on ticketRepo for persistence logic
    TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository= ticketRepository;
    }

    // Key tasks of this class are to generate a ticket, save it, deactivate it
    
    public Ticket generateTicket(Vehicle vehicle, UUID slotId){
        System.out.println("[SERVICE] Generating ticket for vehicle: " + vehicle.getLicensePlate());
        // create a ticket
        Ticket ticket = new Ticket(vehicle.getId(), slotId);
        // save to repo
        ticketRepository.save(ticket);
        System.out.println("[SERVICE] Ticket generated successfully: " + ticket.getId());
        return ticket;
    };

    public Optional<Ticket> getTicket(UUID ticketId){
        System.out.println("[SERVICE] Retrieving ticket: " + ticketId);
        return ticketRepository.findById(ticketId);
    };
    public void deactivateTicket(UUID ticketId){
        System.out.println("[SERVICE] Deactivating ticket: " + ticketId);
        ticketRepository.deactivateTicket(ticketId);

    };

    
}
