package decorator;

public class BaseNotifier implements Notifier {
    private String recipientName;

    public BaseNotifier(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public void send(String message) {
        System.out.println("┌─────────────────────────────────────");
        System.out.println("│ 🔔 Notification for : " + recipientName);
        System.out.println("│ 📝 Message          : " + message);
        System.out.println("│ 📣 Channels         : " + getChannels());
        System.out.println("└─────────────────────────────────────");
    }

    @Override
    public String getChannels() {
        return "[ Base ]";
    }

}
