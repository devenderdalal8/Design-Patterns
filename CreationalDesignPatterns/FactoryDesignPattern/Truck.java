public class Truck implements Vehicle {
    private int wheels = 6;

    @Override
    public void described() {
        System.out.println("I am a Truck with " + wheels + " wheels 🚛");
    }

    @Override
    public int getWheels() {
        return wheels;
    }

}
