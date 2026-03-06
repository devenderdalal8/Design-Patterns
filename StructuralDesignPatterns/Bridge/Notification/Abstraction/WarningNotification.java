package Bridge.Notification.Abstraction;

import Bridge.Notification.Implentation.NotificationSender;

public class WarningNotification extends Notification {
    public WarningNotification(NotificationSender sender) { super(sender); }

    @Override
    public void send(String recipient, String message) {
        System.out.println("--- ⚠️  WARNING Notification ---");
        sender.send(recipient, "⚠️ Warning", message);
    }
}
