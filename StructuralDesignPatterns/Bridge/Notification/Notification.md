# Scenario: Send Info, Warning, Alert notifications via Email, SMS, Slack— any combination.

# Bridge Design Pattern in Java

## 📌 Definition

The **Bridge Pattern** is a structural design pattern that separates an
abstraction from its implementation so that the two can vary
independently.

It is useful when: - You want to avoid a large number of subclass
combinations. - You want to switch implementations at runtime. - You
want abstraction and implementation to evolve independently.

------------------------------------------------------------------------

## 🎯 Problem It Solves

If we combine multiple types of notifications (Info, Warning, Alert)
with multiple sending channels (Email, SMS, Slack, Push), without Bridge
pattern:

-   InfoEmailNotification
-   InfoSmsNotification
-   WarningEmailNotification
-   WarningSmsNotification
-   AlertEmailNotification
-   AlertSmsNotification
-   ... and so on

This leads to **class explosion**.

Bridge pattern solves this by separating:

-   Abstraction → Notification type
-   Implementation → Sender channel

------------------------------------------------------------------------

## 🏗 Structure

### 1️⃣ Abstraction

``` java
public abstract class Notification {
    protected NotificationSender sender;

    public Notification(NotificationSender sender) {
        this.sender = sender;
    }

    public abstract void send(String recipient, String message);

    public void switchSender(NotificationSender newSender) {
        this.sender = newSender;
    }
}
```

### 2️⃣ Refined Abstractions

``` java
public class InfoNotification extends Notification {
    public InfoNotification(NotificationSender sender) { super(sender); }

    @Override
    public void send(String recipient, String message) {
        sender.send(recipient, "Info", message);
    }
}
```

``` java
public class WarningNotification extends Notification {
    public WarningNotification(NotificationSender sender) { super(sender); }

    @Override
    public void send(String recipient, String message) {
        sender.send(recipient, "Warning", message);
    }
}
```

------------------------------------------------------------------------

### 3️⃣ Implementor Interface

``` java
public interface NotificationSender {
    void send(String recipient, String subject, String body);
    String getChannel();
}
```

------------------------------------------------------------------------

### 4️⃣ Concrete Implementations

``` java
public class EmailSender implements NotificationSender {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("EMAIL: " + subject + " - " + body);
    }

    @Override
    public String getChannel() { return "Email"; }
}
```

``` java
public class SmsSender implements NotificationSender {
    @Override
    public void send(String recipient, String subject, String body) {
        System.out.println("SMS: " + subject + ": " + body);
    }

    @Override
    public String getChannel() { return "SMS"; }
}
```

------------------------------------------------------------------------

## 🔄 Runtime Flexibility

``` java
Notification notification = new InfoNotification(new EmailSender());
notification.send("mediatorUser@example.com", "System update");

notification.switchSender(new SmsSender());
notification.send("9876543210", "System update");
```

This shows: - Notification type remains same - Channel can change at
runtime

------------------------------------------------------------------------

## 🧠 Key Components

  Component              Role
  ---------------------- ----------------------------------
  Abstraction            Defines high-level control logic
  Refined Abstraction    Extends abstraction
  Implementor            Defines implementation interface
  Concrete Implementor   Actual implementation details

------------------------------------------------------------------------

## 🚀 Advantages

-   Prevents class explosion
-   Follows Open/Closed Principle
-   Enables runtime switching of implementations
-   Clean separation of concerns

------------------------------------------------------------------------

## ⚠️ When to Use

Use Bridge pattern when:

-   You have two independent dimensions that vary.
-   You want to switch implementations dynamically.
-   You want cleaner, more maintainable architecture.

------------------------------------------------------------------------

## 🏦 Real-World Use Cases

-   Notification systems (Email, SMS, Push)
-   Payment gateways (UPI, Card, NetBanking)
-   Logging frameworks (File, DB, Cloud)
-   Messaging services (Kafka, RabbitMQ, REST)

------------------------------------------------------------------------

## 🔥 Interview Summary (Short)

Bridge pattern separates abstraction from implementation so both can
evolve independently. It prevents subclass explosion and allows runtime
flexibility.

------------------------------------------------------------------------

**End of Notes**
