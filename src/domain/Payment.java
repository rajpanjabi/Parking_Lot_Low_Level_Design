package domain;

import java.util.UUID;

public class Payment {
    private UUID id; 
    private UUID ticketId;
    private double amount;
    private PaymentGateway gateway;
    private PaymentStatus status;

    public enum PaymentStatus{
        PENDING, SUCCESS, FAILED;
    }
    
    public enum PaymentGateway{
        STRIPE, PAYPAL;
    }

    public Payment(UUID ticketId, double amount, PaymentGateway gateway){
        this.id=UUID.randomUUID();
        this.ticketId=ticketId;
        this.amount=amount;
        this.gateway=gateway;
        this.status=PaymentStatus.PENDING;
    }

    public UUID getId(){
        return this.id;
    }

    public UUID getTicketId(){
        return this.ticketId;
    }

    public double getAmount(){
        return this.amount;
    }

    public PaymentStatus getPaymentStatus(){
        return this.status;
    }

    public void markAsSuccess() {
        this.status = PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", amount=" + amount +
                ", gateway=" + gateway +
                ", status=" + status +
                '}';
    }
    
}
