package BehavioralDesignPatterns.Strategy;

import BehavioralDesignPatterns.Strategy.PaymentStrategyMode.CreditCardPaymentStrategy;
import BehavioralDesignPatterns.Strategy.PaymentStrategyMode.CryptoStrategy;
import BehavioralDesignPatterns.Strategy.PaymentStrategyMode.PaypalPaymentStrategy;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardPaymentStrategy("1234567890123456", "123"));
        cart.checkout(99.99);

        // Swap to PayPal at runtime
        cart.setPaymentStrategy(new PaypalPaymentStrategy("user@example.com"));
        cart.checkout(49.99);

        // Swap to Crypto
        cart.setPaymentStrategy(new CryptoStrategy("0xABC123..."));
        cart.checkout(199.00);
    }
}
