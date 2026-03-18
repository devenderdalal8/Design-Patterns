package BehavioralDesignPatterns.Mediator;

public interface ChatMediator {
    void sendMessage(String message , MediatorUser sender);
    void addUser(MediatorUser user);
}
