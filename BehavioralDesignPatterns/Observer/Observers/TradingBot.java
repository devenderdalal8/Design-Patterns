package BehavioralDesignPatterns.Observer.Observers;

import BehavioralDesignPatterns.Observer.Observer;

public class TradingBot implements Observer {
    private double buyThreshold;

    public TradingBot(double buyThreshold) {
        this.buyThreshold = buyThreshold;
    }

    @Override
    public void update(String stockName, double price) {
        if (price <= buyThreshold) {
            System.out.println("  🤖 [TradingBot] BUY triggered for " + stockName + " at $" + price);
        } else {
            System.out.println("  🤖 [TradingBot] Monitoring " + stockName + " at $" + price + " (threshold: $" + buyThreshold + ")");
        }
    }

}
