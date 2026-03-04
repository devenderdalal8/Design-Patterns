package Bridge.Notification.Implentation;

public class SMS implements NotificationSender {

    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("  📱 SMS → To: " + recipient);
        System.out.println("     Message: " + subject + ": " + body);
    }

    @Override
    public String getChannel() {
        return "SMS";
    }

}
