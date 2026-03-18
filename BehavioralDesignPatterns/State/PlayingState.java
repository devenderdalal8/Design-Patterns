package BehavioralDesignPatterns.State;

public class PlayingState implements State{
    @Override
    public void pressButton(MediaPlayer player) {
        System.out.println("Pausing the music");
        player.setState(new PausedState());
    }
}
