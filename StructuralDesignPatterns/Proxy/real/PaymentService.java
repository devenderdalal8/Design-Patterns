package StructuralDesignPatterns.Proxy.real;

public interface PaymentService {
    String processPayment(String userId , double amount);
    String refundPayment(String userId , String transactionId);
    String getPaymentStatus(String transactionId);
}
