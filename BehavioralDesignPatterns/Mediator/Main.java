package BehavioralDesignPatterns.Mediator;

public class Main {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        MediatorUser alice = new PremiumUser(chatRoom, "Alice");
        MediatorUser bob   = new RegularUser(chatRoom, "Bob");
        MediatorUser carol = new RegularUser(chatRoom, "Carol");
        MediatorUser dave  = new PremiumUser(chatRoom, "Dave");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(carol);
        chatRoom.addUser(dave);

        System.out.println("\n--- Chat begins ---");

        alice.send("Hey everyone!");
        bob.send("Hi Alice!");
        carol.send("Hello all!");
        dave.send("Good to see you here.");
    }
}
