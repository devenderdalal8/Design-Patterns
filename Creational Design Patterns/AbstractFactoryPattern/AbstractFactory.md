# Abstract Factory Design Pattern – Complete Lecture Guide

---

# 1️⃣ What is Abstract Factory?

The **Abstract Factory Pattern** is a **Creational Design Pattern** that provides an interface for creating families of related or dependent objects without specifying their concrete classes.

It is also called:

> **Factory of Factories**

Instead of creating objects directly using `new`, we delegate object creation to a factory that produces related objects together.

---

# 2️⃣ Real-World Problem

Imagine we are building a UI library that supports multiple themes:

- 🌙 Dark Theme
- ☀️ Light Theme

Each theme must provide:

- Button
- Checkbox
- TextField

Requirement:
- All UI elements must belong to the same theme.
- Client code must not know which concrete classes are used.
- Adding a new theme should not break existing code.

---

# 3️⃣ Why Not Use Simple Factory?

Simple Factory can create one object type.

But here we need:

- Button
- Checkbox
- TextField

And they must belong to the same family.

Abstract Factory ensures:

✔ Product consistency  
✔ Loose coupling  
✔ Scalability  
✔ Open/Closed Principle compliance  

---

# 4️⃣ Project Structure

```
src/
├── Button.java
├── Checkbox.java
├── TextField.java
│
├── DarkButton.java
├── DarkCheckbox.java
├── DarkTextField.java
│
├── LightButton.java
├── LightCheckbox.java
├── LightTextField.java
│
├── UIFactory.java
├── DarkThemeFactory.java
├── LightThemeFactory.java
│
└── Main.java
```

---

# 5️⃣ Step-by-Step Implementation

---

## Step 1: Product Interfaces

### Button.java

```java
public interface Button {
    void render();
}
```

### Checkbox.java

```java
public interface Checkbox {
    void render();
}
```

### TextField.java

```java
public interface TextField {
    void render();
}
```

---

## Step 2: Dark Theme Products

### DarkButton.java

```java
public class DarkButton implements Button {
    public void render() {
        System.out.println("Rendering Dark Button");
    }
}
```

### DarkCheckbox.java

```java
public class DarkCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Dark Checkbox");
    }
}
```

### DarkTextField.java

```java
public class DarkTextField implements TextField {
    public void render() {
        System.out.println("Rendering Dark TextField");
    }
}
```

---

## Step 3: Light Theme Products

### LightButton.java

```java
public class LightButton implements Button {
    public void render() {
        System.out.println("Rendering Light Button");
    }
}
```

### LightCheckbox.java

```java
public class LightCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Light Checkbox");
    }
}
```

### LightTextField.java

```java
public class LightTextField implements TextField {
    public void render() {
        System.out.println("Rendering Light TextField");
    }
}
```

---

## Step 4: Abstract Factory Interface

### UIFactory.java

```java
public interface UIFactory {

    Button createButton();
    Checkbox createCheckbox();
    TextField createTextField();
}
```

This factory guarantees that all UI components belong to the same theme family.

---

## Step 5: Concrete Factories

### DarkThemeFactory.java

```java
public class DarkThemeFactory implements UIFactory {

    public Button createButton() {
        return new DarkButton();
    }

    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }

    public TextField createTextField() {
        return new DarkTextField();
    }
}
```

---

### LightThemeFactory.java

```java
public class LightThemeFactory implements UIFactory {

    public Button createButton() {
        return new LightButton();
    }

    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }

    public TextField createTextField() {
        return new LightTextField();
    }
}
```

---

## Step 6: Client Code

### Main.java

```java
public class Main {

    public static void main(String[] args) {

        UIFactory factory;

        // Choose theme dynamically
        String theme = "dark";

        if (theme.equalsIgnoreCase("dark")) {
            factory = new DarkThemeFactory();
        } else {
            factory = new LightThemeFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        button.render();
        checkbox.render();
        textField.render();
    }
}
```

---

# 6️⃣ Output (Dark Theme)

```
Rendering Dark Button
Rendering Dark Checkbox
Rendering Dark TextField
```

---

# 7️⃣ How It Works Internally

Instead of:

```java
new DarkButton();
new DarkCheckbox();
new DarkTextField();
```

We do:

```java
factory.createButton();
factory.createCheckbox();
factory.createTextField();
```

The client depends only on:

```
UIFactory
Button
Checkbox
TextField
```

NOT on concrete implementations.

This ensures:

- Loose coupling
- Product family consistency
- Easy theme switching

---

# 8️⃣ Benefits

✔ Enforces product compatibility  
✔ Removes direct dependency on concrete classes  
✔ Easy to add new themes  
✔ Follows SOLID principles  
✔ Clean scalable architecture  

---

# 9️⃣ When To Use Abstract Factory

Use it when:

- System has multiple families of related objects
- You want to enforce product consistency
- Object creation logic must be centralized
- You want extensibility without modifying existing code

---

# 🔟 Factory Method vs Abstract Factory

| Feature | Factory Method | Abstract Factory |
|----------|----------------|-----------------|
| Creates | One product | Family of products |
| Complexity | Simple | Structured |
| Pattern Type | Single factory | Factory of factories |

---

# 1️⃣1️⃣ Real World Examples

- Cross-platform UI frameworks
- Database drivers (MySQL, PostgreSQL)
- Payment gateways
- Cloud providers (AWS, Azure, GCP)

---

# 1️⃣2️⃣ Summary

Abstract Factory:

- Creates families of related objects
- Promotes loose coupling
- Supports scalability
- Ensures product consistency
- Ideal for theme-based or platform-based systems

---

# 🎓 Interview One-Liner

> Abstract Factory is a creational design pattern that provides an interface for creating families of related objects without specifying their concrete classes.