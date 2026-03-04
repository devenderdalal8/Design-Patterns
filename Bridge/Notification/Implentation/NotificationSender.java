package Bridge.Notification.Implentation;

public interface NotificationSender {
    void send(String recipient, String subject, String body);
    String getChannel();
}
