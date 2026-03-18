# Factory Design Pattern

The Factory Design Pattern is a creational pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. This pattern delegates the responsibility of object instantiation to separate factory classes.

## 📌 Key Concepts

- **Creator (Factory):** Defines an interface for creating objects. In our implementation, `VehicleFactory` acts as the factory.
- **Product:** The interface or abstract class that defines the type of objects the factory creates. Here, `Vehicle` is the product interface.
- **Concrete Products:** Classes that implement the `Vehicle` interface such as `Car`, `Bike`, and `Truck`.

## 🧱 Project Structure

```
FactoryDesignPattern/
    ├─ Vehicle.java          # product interface
    ├─ Car.java              # concrete product
    ├─ Bike.java             # concrete product
    ├─ Truck.java            # concrete product
    ├─ VehicleFactory.java   # factory class for creating vehicles
    └─ Main.java             # demo client code
```

## 🔄 How It Works

1. The client code (`Main.java`) requests a vehicle type from the factory.
2. `VehicleFactory` examines the provided `String` identifier (e.g., "car", "bike", "truck").
3. Based on the input, the factory instantiates and returns the appropriate concrete `Vehicle`.
4. The client interacts with the returned `Vehicle` through its common interface without needing to know the concrete class.

This decouples object creation from the client and makes it easier to add new vehicle types with minimal changes.

## 🚀 Example Usage (Main.java)
```java
public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        car.drive();

        Vehicle bike = VehicleFactory.createVehicle("bike");
        bike.drive();

        Vehicle truck = VehicleFactory.createVehicle("truck");
        truck.drive();
    }
}
```

The output demonstrates that each vehicle type behaves polymorphically:
```
Driving a car...
Riding a bike...
Driving a truck...
```

## ✅ Benefits

- Promotes loose coupling between client and concrete implementations.
- Simplifies unit testing by allowing the replacement of real objects with mocks/stubs.
- Eases the addition of new product types.

## 📝 Notes

- The factory can be extended to use reflection or configuration files to support dynamic product registration.
- Variants include the **Abstract Factory** (for families of products) and **Factory Method** (uses inheritance).

---

> 🍀 **Tip:** Keep your factory methods simple and focused. If the creation logic becomes complex, consider splitting responsibilities or using a Builder alongside the factory.
