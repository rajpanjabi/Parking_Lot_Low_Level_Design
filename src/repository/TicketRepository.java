package repository;

import domain.Ticket;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRepository {
    // this repository will simulate all db operations related to ticket
    // it handles saving a ticket, deactivating it

    private Map<UUID, Ticket> tickets = new ConcurrentHashMap<>();

    public Ticket save(Ticket ticket){
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket deactivateTicket(UUID ticketId){
        Ticket ticket=tickets.get(ticketId);
        ticket.deactivate();
        return ticket;
    }

    public Optional<Ticket> findById(UUID ticketId){
        return Optional.ofNullable(tickets.get(ticketId));
    }

    public List<Ticket> getActiveTickets(){
        return tickets.values().stream().filter(Ticket:: isActive).toList();
    }


    public void clear() {
        tickets.clear();
    }



}
