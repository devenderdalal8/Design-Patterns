package BehavioralDesignPatterns.Strategy.PaymentStrategyMode;

import BehavioralDesignPatterns.Strategy.PaymentStrategy;

public class CryptoStrategy implements PaymentStrategy {

    private final String walletAddress;

    public CryptoStrategy(String walletAddress){
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Crypto wallet: " + walletAddress);
    }
}
