package BehavioralDesignPatterns.Observer.Observers;

import BehavioralDesignPatterns.Observer.Observer;

public class MobileApp implements Observer {
    String userName;

    public MobileApp(String name) {
        this.userName = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("  📱 [Mobile - " + userName + "] " + stockName + " is now $" + price);
    }
}
