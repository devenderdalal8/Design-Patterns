package BehavioralDesignPatterns.Mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<MediatorUser> users = new ArrayList<>();



    @Override
    public void sendMessage(String message, MediatorUser sender) {
        for (MediatorUser user : users) {
            // Don't send message back to the sender
            if (!user.equals(sender)) {
                user.receive(message, sender.getName());
            }
        }
    }

    @Override
    public void addUser(MediatorUser user) {
        users.add(user);
        System.out.println("  [ChatRoom] " + user.getName() + " joined the chat.");
    }
}
