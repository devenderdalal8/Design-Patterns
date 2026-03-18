package BehavioralDesignPatterns.State;

class PausedState implements State {
    @Override
    public void pressButton(MediaPlayer player) {
        System.out.println("Playing the music");
        player.setState(new PlayingState());
    }
}