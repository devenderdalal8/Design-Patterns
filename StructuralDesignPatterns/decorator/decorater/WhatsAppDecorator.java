package decorator.decorater;

import decorator.Notifier;

// WhatsAppDecorator.java
public class WhatsAppDecorator extends NotifierDecorator {
    private String whatsappNumber;

    public WhatsAppDecorator(Notifier notifier, String whatsappNumber) {
        super(notifier);
        this.whatsappNumber = whatsappNumber;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        sendWhatsApp(message);
    }

    private void sendWhatsApp(String message) {
        System.out.println("  🟢 WHATSAPP sent");
        System.out.println("     To      : " + whatsappNumber);
        System.out.println("     Message : " + message);
    }

    @Override
    public String getChannels() {
        return wrappee.getChannels() + " + WhatsApp";
    }
}