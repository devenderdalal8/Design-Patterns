package BehavioralDesignPatterns.ChainOfResponsibility.Loggers;

import BehavioralDesignPatterns.ChainOfResponsibility.base.Logger;

public class ErrorLogger extends Logger {
    public static final int ERROR = 4;

    public ErrorLogger() {
        this.level = ERROR;
    }

    @Override
    protected void write(String message) {
        System.err.println("[ERROR] " + message);
    }
}
