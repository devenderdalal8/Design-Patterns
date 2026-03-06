package decorator.decorater;

import decorator.Notifier;

// EmailDecorator.java
public class EmailDecorator extends NotifierDecorator {
    private String emailAddress;

    public EmailDecorator(Notifier notifier, String emailAddress) {
        super(notifier);
        this.emailAddress = emailAddress;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);       // send via inner layers first
        sendEmail(message);          // then add Email on top
    }

    private void sendEmail(String message) {
        System.out.println("  📧 EMAIL sent");
        System.out.println("     To      : " + emailAddress);
        System.out.println("     Subject : Notification Alert");
        System.out.println("     Body    : " + message);
    }

    @Override
    public String getChannels() {
        return wrappee.getChannels() + " + Email";
    }
}
