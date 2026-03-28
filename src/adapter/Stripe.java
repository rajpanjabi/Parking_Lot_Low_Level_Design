package adapter;
import java.util.Random;
import java.util.UUID;

public class Stripe implements PaymentGatewayAdapter {
    
    @Override
    public boolean pay(UUID ticketId, double amount) {
        // Simulate payment processing
        System.out.println("[ADAPTER] StripeAdapter processing payment for ticket: " + ticketId + " amount: " + amount);
        
        // Simulate 90% success rate
        Random random = new Random();
        boolean success = random.nextDouble() < 0.9;
        
        System.out.println("[ADAPTER] StripeAdapter payment result: " + (success ? "SUCCESS" : "FAILED"));
        
        return success;
    }
} 