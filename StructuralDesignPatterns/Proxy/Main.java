package StructuralDesignPatterns.Proxy;

import StructuralDesignPatterns.Proxy.proxy.PaymentServiceProxy;
import StructuralDesignPatterns.Proxy.real.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentService gateway = new PaymentServiceProxy();

        // ── Test 1: Normal valid user ──────────────────────────
        System.out.println("\n========== USR-001 (Valid user) ==========");
        gateway.processPayment("USR-001", 1500.00);
    
        gateway.processPayment("USR-001", 2400.00);
        gateway.processPayment("USR-001", 999.00);

        // ── Test 2: Rate limit kicks in ────────────────────────
        System.out.println("\n========== USR-001 exceeds rate limit =====");
        gateway.processPayment("USR-001", 500.00); // 4th request — BLOCKED

        // ── Test 3: Invalid API key (bad actor) ────────────────
        System.out.println("\n========== USR-999 (Invalid API key) ======");
        gateway.processPayment("USR-999", 99999.00);

        // ── Test 4: Status check (open endpoint) ──────────────
        System.out.println("\n========== Status Check (open) ============");
        gateway.getPaymentStatus("TXN-17123456789");

        // ── Test 5: Refund by valid user ───────────────────────
        System.out.println("\n========== USR-002 Refund ==================");
        gateway.refundPayment("USR-002", "TXN-17123456789");
    }
}