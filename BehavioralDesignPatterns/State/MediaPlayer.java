package BehavioralDesignPatterns.State;

import BehavioralDesignPatterns.Mediator.ChatMediator;

public class MediaPlayer {
    private State state;

    public MediaPlayer() {
        state = new PausedState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressButton() {
        state.pressButton(this);
    }
}
