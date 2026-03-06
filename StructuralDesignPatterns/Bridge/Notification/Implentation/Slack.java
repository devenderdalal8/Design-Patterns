package Bridge.Notification.Implentation;

public class Slack implements NotificationSender {
    private String webhookUrl;

    public Slack(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("  💬 SLACK → Channel: " + recipient);
        System.out.println("     *" + subject + "*\n     " + body);
    }

    @Override
    public String getChannel() {
        return "Slack";
    }

}
