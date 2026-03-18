package BehavioralDesignPatterns.State;

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.pressButton(); // Playing
        player.pressButton(); // Pausing
        player.pressButton(); // Playing
    }
}