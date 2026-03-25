package domain;
import domain.Payment.PaymentStatus;
import java.time.LocalDateTime;
import java.util.UUID;


public class Receipt {
    private UUID id;
    private UUID ticketId;
    private LocalDateTime exitTime;
    private double totalPayment;
    private Payment.PaymentStatus paymentStatus;


    public Receipt(UUID ticketId,double totalPayment){
        this.id=UUID.randomUUID();
        this.ticketId=ticketId;
        this.exitTime=LocalDateTime.now();
        this.totalPayment=totalPayment;
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

    public double getTotalPayment(){
        return this.totalPayment;
    }

    public void setPaymentSuccess(){
        this.paymentStatus=PaymentStatus.SUCCESS;
    }

    public void setPaymentFailure(){
        this.paymentStatus=PaymentStatus.FAILED;
    }


}
