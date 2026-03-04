package Bridge.Notification.Implentation;

public class Email implements NotificationSender{

    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("  📧 EMAIL → To: " + recipient);
        System.out.println("     Subject: " + subject);
        System.out.println("     Body: " + body);
    }

    @Override
    public String getChannel() {
        return "Email";
    }
    
}
