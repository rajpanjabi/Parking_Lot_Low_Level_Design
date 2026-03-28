package domain;
import domain.Payment.PaymentStatus;
import java.time.LocalDateTime;
import java.util.UUID;


public class Receipt {
    private UUID id;
    private UUID ticketId;
    private LocalDateTime exitTime;
    private double totalFee;
    private Payment.PaymentStatus paymentStatus;


    public Receipt(UUID ticketId,double totalFee){
        this.id=UUID.randomUUID();
        this.ticketId=ticketId;
        this.exitTime=LocalDateTime.now();
        this.totalFee=totalFee;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public UUID getId(){
        return this.id;
    }

    public UUID getTicketId(){
        return this.ticketId;
    }

    public LocalDateTime getExitTime(){
        return this.exitTime;
    }

    public double getTotalFee(){
        return this.totalFee;
    }

    public Payment.PaymentStatus getPaymentStatus(){
        return this.paymentStatus;
    }

    public void markAsPaid() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }

    public void markAsFailed() {
        this.paymentStatus = PaymentStatus.FAILED;
    }


}
