package BehavioralDesignPatterns.ChainOfResponsibility.base;

public enum Level {
    DEBUG(1) , INFO(2) , WARN(3) , ERROR(4);

    private final int level ;

    Level(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
