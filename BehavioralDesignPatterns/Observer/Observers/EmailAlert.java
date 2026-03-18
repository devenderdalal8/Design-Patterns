package BehavioralDesignPatterns.Observer.Observers;

import BehavioralDesignPatterns.Observer.Observer;

public class EmailAlert implements Observer {
    private String email;

    public EmailAlert(String email) {
        this.email = email;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("  📧 [Email → " + email + "] Alert: " + stockName + " changed to $" + price);
    }
}
