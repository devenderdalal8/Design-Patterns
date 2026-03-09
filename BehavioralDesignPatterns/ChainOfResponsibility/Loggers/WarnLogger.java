package BehavioralDesignPatterns.ChainOfResponsibility.Loggers;

import BehavioralDesignPatterns.ChainOfResponsibility.base.Logger;

public class WarnLogger extends Logger {
    public static final int WARN = 3;

    public WarnLogger() {
        this.level = WARN;
    }

    @Override
    protected void write(String message) {
        System.out.println("[WARN]  " + message);
    }
}