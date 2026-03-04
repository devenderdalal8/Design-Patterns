package Bridge.Notification.Abstraction;

import Bridge.Notification.Implentation.NotificationSender;

public class InfoNotification extends Notification {
    public InfoNotification(NotificationSender sender) { super(sender); }

    @Override
    public void send(String recipient, String message) {
        System.out.println("--- ℹ️  INFO Notification ---");
        sender.send(recipient, "ℹ️ Info", message);
    }
}