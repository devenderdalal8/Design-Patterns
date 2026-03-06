package decorator.decorater;

import decorator.Notifier;

// SmsDecorator.java
public class SmsDecorator extends NotifierDecorator {
    private String phoneNumber;

    public SmsDecorator(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        sendSms(message);
    }

    private void sendSms(String message) {
        // SMS has 160 char limit — decorator handles truncation!
        String smsText = message.length() > 160
                ? message.substring(0, 157) + "..."
                : message;
        System.out.println("  📱 SMS sent");
        System.out.println("     To  : " + phoneNumber);
        System.out.println("     Text: " + smsText);
    }

    @Override
    public String getChannels() {
        return wrappee.getChannels() + " + SMS";
    }
}
