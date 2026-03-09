package BehavioralDesignPatterns.ChainOfResponsibility.base;

public abstract class Logger {
    protected int level;
    protected Logger nextLogger;

    public Logger setNext(Logger nextLogger){
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    protected abstract void write(String message);

    public void log(Level levels , String message){
        if(levels.getLevel() <= level){
            write(message);
        }
        if(nextLogger != null){
            nextLogger.log(levels, message);
        }
    }
}
