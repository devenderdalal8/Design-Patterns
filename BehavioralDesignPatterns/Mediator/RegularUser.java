package BehavioralDesignPatterns.Mediator;

// Regular user
public class RegularUser extends MediatorUser {

    public RegularUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println("\n💬 [" + name + "] sends: \"" + message + "\"");
        chatMediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String senderName) {
        System.out.println("   📩 [" + name + "] received from " + senderName + ": \"" + message + "\"");
    }
}

