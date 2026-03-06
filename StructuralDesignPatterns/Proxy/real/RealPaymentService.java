package StructuralDesignPatterns.Proxy.real;

public class RealPaymentService implements PaymentService {

    @Override
    public String processPayment(String userId, double amount) {
        String txnId = "TXN-" + System.currentTimeMillis();
        System.out.println("[PaymentService] ✔ Payment processed."
                + " User: " + userId
                + " | Amount: ₹" + amount
                + " | TxnId: " + txnId);
        return txnId;
    }

    @Override
    public String refundPayment(String userId, String transactionId) {
        System.out.println("[PaymentService] ↩ Refund processed."
                + " User: " + userId
                + " | TxnId: " + transactionId);
        return "REFUND-" + transactionId;
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        System.out.println("[PaymentService] Fetching status for: " + transactionId);
        return "SUCCESS";
    }

}
