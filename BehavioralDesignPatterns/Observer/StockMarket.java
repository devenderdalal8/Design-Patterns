package BehavioralDesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Subject{

    private final List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public void setStockPrice(String stockName , double price){
        System.out.println("\n📈 Stock Update: " + stockName + " → $" + price);
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update(stockName , price);
        });
    }
}
