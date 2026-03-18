package BehavioralDesignPatterns.Strategy.PaymentStrategyMode;

import BehavioralDesignPatterns.Strategy.PaymentStrategy;

public class PaypalPaymentStrategy implements PaymentStrategy {

    public  final String email;

    public  PaypalPaymentStrategy(String email){
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}
