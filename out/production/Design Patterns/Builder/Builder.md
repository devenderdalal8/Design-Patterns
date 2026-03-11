# Builder Design Pattern – Implementation Documentation

---

# 1️⃣ Overview

This project demonstrates the **Builder Design Pattern** in Java.

The Builder pattern is used to construct complex objects step-by-step while keeping the object creation process flexible and readable.

In this example, we build a `User` object with:

- Required fields:
  - `name`
  - `email`

- Optional fields:
  - `age`
  - `address`
  - `phone`

---

# 2️⃣ Why Builder Pattern?

Without Builder, object creation may look like this:

```java
new User("John", "john@gmail.com", 28, "Chennai", "555-1234");
```

Problems:
- Hard to read
- Order dependent
- Constructor explosion
- Optional parameters become messy

Builder solves these issues by:

✔ Improving readability  
✔ Supporting optional parameters  
✔ Allowing step-by-step object construction  
✔ Avoiding telescoping constructors  

---

# 3️⃣ Project Structure

```
Builder/
├── Main.java
└── User.java
```

---

# 4️⃣ Implementation Details

---

## 4.1 User Class

The `User` class:

- Contains private fields
- Has a constructor that accepts a `Builder`
- Is created only via `Builder`
- Overrides `toString()` for display

```java
package Builder;

public class User {
    private String name;
    private String email;
    private int age;
    private String address;
    private String phone;

    public User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    public String toString() {
        return "User{name='" + name + "', email='" + email +
                "', age=" + age + ", address='" + address +
                "', phone='" + phone + "'}";
    }
```

---

## 4.2 Static Builder Class

The static `Builder` class:

- Holds the same fields as `User`
- Has a constructor for required fields
- Provides setter-style methods for optional fields
- Returns `this` for method chaining
- Has a `build()` method to create the `User` object

```java
    public static class Builder {
        private String name;
        private String email;
        private int age;
        private String address;
        private String phone;

        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

---

# 5️⃣ Client Code (Main.java)

This class demonstrates how to create different `User` objects using the Builder pattern.

```java
package Builder;

public class Main {
    public static void main(String[] args) {

        // Only required fields
        User user1 = new User.Builder("John", "John@gmail.com").build();
        System.out.println(user1);

        // Required + optional fields
        User user2 = new User.Builder("Jane", "Smith")
                .setEmail("jane@example.com")
                .setPhone("555-1234")
                .setAge(28)
                .build();
        System.out.println(user2);

        // All fields
        User user3 = new User.Builder("Bob", "Johnson")
                .setEmail("bob@example.com")
                .setPhone("555-5678")
                .setAddress("123 Main St")
                .setAge(35)
                .build();
        System.out.println(user3);
    }
}
```

---

# 6️⃣ Output

```
User{name='John', email='John@gmail.com', age=0, address='null', phone='null'}
User{name='Jane', email='jane@example.com', age=28, address='null', phone='555-1234'}
User{name='Bob', email='bob@example.com', age=35, address='123 Main St', phone='555-5678'}
```

---

# 7️⃣ Key Concepts Demonstrated

### 1. Static Inner Builder Class
Allows object creation without exposing constructor directly.

### 2. Method Chaining
Each setter returns `this`, enabling:

```java
.setEmail(...)
.setPhone(...)
.setAge(...)
```

### 3. Encapsulation
User object fields are only set via Builder.

---

# 8️⃣ Advantages of Builder Pattern

✔ Clean and readable object creation  
✔ No constructor overload explosion  
✔ Supports optional parameters  
✔ Flexible object creation  
✔ Improves maintainability  

---

# 9️⃣ When to Use Builder Pattern

Use Builder when:

- A class has many parameters
- Some parameters are optional
- You want readable and fluent object creation
- Constructor becomes too large
- You want better maintainability

---

# 🔟 Interview One-Liner

> The Builder pattern constructs complex objects step-by-step and allows creation of immutable or flexible objects without using large constructors.

---

# 1️⃣1️⃣ Improvement Suggestion (Best Practice)

To make this implementation more robust:

- Make `User` fields `final`
- Add validation inside `build()` method
- Set default values for optional fields

Example:

```java
public User build() {
    if (name == null || email == null) {
        throw new IllegalStateException("Name and Email are required");
    }
    return new User(this);
}
```

---

# ✅ Conclusion

This implementation demonstrates:

- How Builder separates construction logic from representation
- How method chaining improves readability
- How optional parameters are handled cleanly

The Builder pattern is widely used in:

- Spring Framework
- HTTP request builders
- Configuration objects
- Large domain models