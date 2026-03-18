package BehavioralDesignPatterns.Observer;

import BehavioralDesignPatterns.Observer.Observers.EmailAlert;
import BehavioralDesignPatterns.Observer.Observers.MobileApp;
import BehavioralDesignPatterns.Observer.Observers.TradingBot;

public class Main {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        // Register observers
        Observer alice = new MobileApp("Alice");
        Observer bob = new EmailAlert("bob@example.com");
        Observer bot = new TradingBot(150.00);

        market.addObserver(alice);
        market.addObserver(bob);
        market.addObserver(bot);

        // Trigger state changes
        market.setStockPrice("AAPL", 178.50);
        market.setStockPrice("AAPL", 145.00); // Bot should trigger buy!

        // Bob unsubscribes
        System.out.println("\n🔕 Bob unsubscribed.");
        market.removeObserver(bob);

        market.setStockPrice("AAPL", 160.00); // Bob won't be notified
    }
}
