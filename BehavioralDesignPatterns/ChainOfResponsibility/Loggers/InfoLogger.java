package BehavioralDesignPatterns.ChainOfResponsibility.Loggers;

import BehavioralDesignPatterns.ChainOfResponsibility.base.Logger;

public class InfoLogger extends Logger {
    private static final int INFO = 2;

    public InfoLogger(){
        this.level = INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("[INFO]  " + message);
    }
    
}
