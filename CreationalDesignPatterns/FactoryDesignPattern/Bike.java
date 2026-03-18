public class Bike implements Vehicle {
    private int wheels = 2;

    @Override
    public void described() {
        System.out.println("I am a Bike with " + wheels + " wheels ");
    }

    @Override
    public int getWheels() {
        return wheels;
    }
}
