package Bridge.Notification.Abstraction;

import java.util.List;

import Bridge.Notification.Implentation.NotificationSender;

// AlertNotification.java (Critical — sends to multiple channels!)
public class AlertNotification extends Notification {
    private List<NotificationSender> allSenders;

    public AlertNotification(NotificationSender primary,
                             List<NotificationSender> allSenders) {
        super(primary);
        this.allSenders = allSenders;
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("--- 🚨 CRITICAL ALERT — Broadcasting to ALL channels ---");
        for (NotificationSender s : allSenders) {
            s.send(recipient, "🚨 CRITICAL ALERT", message);
            System.out.println();
        }
    }
}
