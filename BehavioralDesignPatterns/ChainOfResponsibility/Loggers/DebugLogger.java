package BehavioralDesignPatterns.ChainOfResponsibility.Loggers;

import BehavioralDesignPatterns.ChainOfResponsibility.base.Logger;

public class DebugLogger extends Logger {
    private static final int DUBG = 1;

    public DebugLogger() {
        this.level = DUBG;
    }

    @Override
    protected void write(String message) {
        System.out.println("[DEBUG] " + message);
    }

}
