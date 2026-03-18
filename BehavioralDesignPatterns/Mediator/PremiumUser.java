package BehavioralDesignPatterns.Mediator;

// Premium user — messages get a VIP tag
public class PremiumUser extends MediatorUser {

    public PremiumUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println("\n⭐ [" + name + " - VIP] sends: \"" + message + "\"");
        chatMediator.sendMessage("[VIP] " + message, this);
    }

    @Override
    public void receive(String message, String sender) {
        System.out.println("   📩 [" + name + "] received from " + sender + ": \"" + message + "\"");
    }
}
