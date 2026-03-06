package decorator.decorater;

import decorator.Notifier;

public abstract class NotifierDecorator implements Notifier {

    protected Notifier wrappee;

    public NotifierDecorator(Notifier notifier) {
        this.wrappee = notifier;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);   // delegate to inner notifier
    }

    @Override
    public String getChannels() {
        return wrappee.getChannels();
    }
}
