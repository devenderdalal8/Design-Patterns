package Facade;

public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        // Client only needs to know these two methods — complexity is hidden!
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}