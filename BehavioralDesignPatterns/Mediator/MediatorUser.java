package BehavioralDesignPatterns.Mediator;

public abstract class MediatorUser {
    protected ChatMediator chatMediator;
    protected String name;

    public MediatorUser(ChatMediator chatMediator, String name) {
        this.chatMediator = chatMediator;
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void send(String message);
    public abstract void receive(String message, String senderName);
}
