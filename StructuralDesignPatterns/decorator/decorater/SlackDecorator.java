package decorator.decorater;

import decorator.Notifier;

// SlackDecorator.java
public class SlackDecorator extends NotifierDecorator {
    private String slackChannel;

    public SlackDecorator(Notifier notifier, String slackChannel) {
        super(notifier);
        this.slackChannel = slackChannel;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("  💬 SLACK sent");
        System.out.println("     Channel : " + slackChannel);
        System.out.println("     Message : *Alert* — " + message);
    }

    @Override
    public String getChannels() {
        return wrappee.getChannels() + " + Slack";
    }
}
