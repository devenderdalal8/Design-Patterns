# Design Patterns Documentation

This repository contains implementations and explanations of common Software Design Patterns. Design patterns are typical solutions to common problems in software design.

## 1. Creational Patterns
*Focus on mechanisms for object creation.*

### Singleton
- **Intent:** Ensures a class has only one instance and provides a global point of access to it.
- **Use Case:** Database connections, Logger classes, Configuration settings.

### Factory Method
- **Intent:** Provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
- **Use Case:** When a system should be independent of how its products are created.

### Abstract Factory
- **Intent:** Lets you produce families of related objects without specifying their concrete classes.
- **Use Case:** UI Toolkits that need to work across different Operating Systems (Windows/Mac/Linux).

### Builder
- **Intent:** Lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.
- **Use Case:** Creating a complex `HTTP Request` or a `User` object with many optional fields.

---

## 2. Structural Patterns
*Focus on how to assemble objects and classes into larger structures.*

### Adapter
- **Intent:** Allows objects with incompatible interfaces to collaborate.
- **Use Case:** Integrating a 3rd party legacy library into a modern system.

### Decorator
- **Intent:** Lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.
- **Use Case:** Adding features to a UI component (e.g., adding a scrollbar to a text area) or adding compression/encryption to data streams.

### Facade
- **Intent:** Provides a simplified interface to a library, a framework, or any other complex set of classes.
- **Use Case:** A single `Order` method that internally handles Inventory, Shipping, and Payment systems.

### Proxy
- **Intent:** Lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.
- **Use Case:** Lazy loading, Access control, Logging.

---

## 3. Behavioral Patterns
*Focus on communication between objects.*

### Observer
- **Intent:** Lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
- **Use Case:** Event handling systems, News feeds, Stock market updates.

### Strategy
- **Intent:** Lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
- **Use Case:** Different payment methods (Credit Card, PayPal, Bitcoin) in an e-commerce app.

### Command
- **Intent:** Turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations.
- **Use Case:** GUI Buttons, Undo/Redo functionality.

### State
- **Intent:** Lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.
- **Use Case:** Document workflow (Draft -> Moderation -> Published).

---

## Summary Table

| Pattern | Category | Purpose |
| :--- | :--- | :--- |
| **Singleton** | Creational | Single instance |
| **Factory** | Creational | Object creation flexibility |
| **Adapter** | Structural | Interface compatibility |
| **Decorator** | Structural | Dynamic behavior addition |
| **Observer** | Behavioral | Event notification |
| **Strategy** | Behavioral | Swappable algorithms |

## How to use this repository
1. Navigate to the specific pattern directory.
2. Read the `example` file to see the implementation.
3. Run the associated test or main file to see the pattern in action.

---
*Documentation generated for Design Patterns Project.*