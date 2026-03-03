public class Car implements Vehicle {
    private int wheels = 4;

    @Override
    public void described() {
        System.out.println("I am a Car with " + wheels + " wheels 🚗");
    }

    @Override
    public int getWheels() {
        return wheels;
    }
}
