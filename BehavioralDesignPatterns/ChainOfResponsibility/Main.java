package BehavioralDesignPatterns.ChainOfResponsibility;

import BehavioralDesignPatterns.ChainOfResponsibility.Loggers.*;
import BehavioralDesignPatterns.ChainOfResponsibility.base.*;

public class Main {
    private static Logger buildChain() {
        Logger debLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();
        Logger warnLogger = new WarnLogger();
        Logger errorLogger = new ErrorLogger();

        // Chain: DEBUG → INFO → WARN → ERROR
        debLogger.setNext(infoLogger).setNext(warnLogger).setNext(errorLogger);

        return debLogger;
    }

    public static void main(String[] args) {
        Logger logger = buildChain();

        System.out.println("--- Logging DEBUG ---");
        logger.log(Level.DEBUG, "Entering method calculateTotal()");

        System.out.println("\n--- Logging INFO ---");
        logger.log(Level.INFO, "MediatorUser 'alice' logged in successfully");

        System.out.println("\n--- Logging WARN ---");
        logger.log(Level.WARN, "Disk usage above 80%");

        System.out.println("\n--- Logging ERROR ---");
        logger.log(Level.ERROR, "Database connection failed!");
    }
}
