package Facade;

public class HomeTheaterFacade {
    private Television tv;
    private SoundSystem sound;
    private DVDPlayer dvd;
    private Lights lights;

    HomeTheaterFacade(){
        this.dvd = new DVDPlayer();
        this.tv = new Television();
        this.sound = new SoundSystem();
        this.lights = new Lights();
    }

    public void watchMovie(String movie) {
        System.out.println("\n--- Get ready to watch a movie! ---");
        lights.dim(20);
        tv.turnOn();
        sound.turnOn();
        sound.setVolume(15);
        dvd.turnOn();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("\n--- Shutting down the home theater ---");
        dvd.stop();
        dvd.turnOff();
        sound.turnOff();
        tv.turnOff();
        lights.brighten();
    }
}
