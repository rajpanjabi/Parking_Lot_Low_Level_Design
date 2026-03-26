package controller;

import domain.Payment;
import domain.Receipt;
import domain.Ticket;

import java.util.Optional;
import java.util.UUID;

import service.PaymentService;
import service.PricingService;
import service.ReceiptService;
import service.SlotService;
import service.TicketService;

public class ExitController {
    // entry point for exit workflow
    // dependencies
    private TicketService ticketService;
    private PricingService pricingService;
    private PaymentService paymentService;
    private SlotService slotService;
    private ReceiptService receiptService;

    // constructor
    public ExitController(TicketService ticketService,
    PricingService pricingService,
    PaymentService paymentService,
    SlotService slotService,
    ReceiptService receiptService){
        this.ticketService=ticketService;
        this.pricingService=pricingService;
        this.paymentService=paymentService;
        this.slotService=slotService;
        this.receiptService=receiptService;
        System.out.println("[CONTROLLER] ExitController initialized");
    }

    public ExitResponse exitVehicle(UUID ticketId){
        System.out.println("[Controller] Vehicle exit request - Ticket: " + ticketId);
        try {
            Optional<Ticket> ticketOpt =ticketService.getTicket(ticketId);
            if(ticketOpt.isEmpty()){
                return new ExitResponse(false, ticketId, 0, "No ticket found with this ticketId");
            }

            Ticket ticket=ticketOpt.get();
            // Make sure if ticket is active
            if (! ticket.isActive()){
                return new ExitResponse(false, ticketId, 0, "Ticket not active");
            }

            // Calculate fees
            double fee=pricingService.calculateFee(ticketId);
            System.out.println("[CONTROLLER] Fee calculated: " + fee);

            // Process Payment
            Payment.PaymentStatus paymentStatus= PaymentService.processPayment(ticketId, fee);
            System.out.println("[CONTROLLER] Payment status: " + paymentStatus);
            if (paymentStatus==Payment.PaymentStatus.FAILED){
                return new ExitResponse(false, ticketId, fee, "Payment Failed");
            }

            // Generate Receipt
            Receipt receipt=ReceiptService.generateReceipt(ticket,fee);
            receiptService.markReceiptAsPaid(receipt);

            // Release slot
            slotService.releaseSlot(ticket.getSlotId());
            
            // Deactivate ticket
            ticketService.deactivateTicket(ticketId);
            
            System.out.println("[CONTROLLER] Vehicle exit successful - Receipt: " + receipt.getId());
            return new ExitResponse(true, receipt.getId(), fee, "Exit successful");
            
        } catch (Exception e) {
            System.out.println("[CONTROLLER] Vehicle exit failed: " + e.getMessage());
            return new ExitResponse(false, null, 0.0, e.getMessage());
        
        }
    
    };



    public static class ExitResponse{
        private final boolean success;
        private final UUID receiptId;
        private final double fee;
        private final String message;

        public ExitResponse(boolean success, UUID receiptId, double fee, String message){
            this.success=success;
            this.receiptId=receiptId;
            this.fee=fee;
            this.message=message;
        }

        public boolean isSuccess(){ return success;}
        public UUID getReceiptId(){ return receiptId;}
        public double getFee(){ return fee;}
        public String getMessage(){ return message;}
    }
}
