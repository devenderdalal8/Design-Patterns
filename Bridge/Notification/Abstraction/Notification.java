package Bridge.Notification.Abstraction;

import Bridge.Notification.Implentation.NotificationSender;

public abstract class Notification {
    protected NotificationSender sender;

    Notification(NotificationSender sender){
        this.sender = sender;
    }

    public abstract void send(String recipient, String message);

     public void switchSender(NotificationSender newSender) {
        System.out.println("  🔄 Switching channel: "
            + sender.getChannel() + " → " + newSender.getChannel());
        this.sender = newSender;
    }
    
}
