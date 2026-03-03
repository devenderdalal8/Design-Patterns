public class Main {
    public static void main(String[] args) {
        // bike
        Vehicle bike = VehicleFactory.create("Bike");
        bike.described();

        // Car
        Vehicle car = VehicleFactory.create("Car");
        car.described();

        // Truck
        Vehicle truck = VehicleFactory.create("Truck");
        truck.described();
    }
}
