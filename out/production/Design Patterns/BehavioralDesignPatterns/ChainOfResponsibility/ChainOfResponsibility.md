# Chain of Responsibility Pattern

> A behavioral design pattern where requests are passed along a chain of handlers. Each handler decides to process the request or forward it to the next handler.

---

## Table of Contents

- [Overview](#overview)
- [Key Concepts](#key-concepts)
- [UML Structure](#uml-structure)
- [Logger System Example](#logger-system-example)
  - [Abstract Handler](#1-abstract-handler)
  - [Concrete Handlers](#2-concrete-handlers)
  - [Client — Building the Chain](#3-client--building-the-chain)
  - [Output](#output)
- [How the Chain Works](#how-the-chain-works)
- [When to Use](#when-to-use)
- [Pros & Cons](#pros--cons)
- [Related Patterns](#related-patterns)

---

## Overview

The **Chain of Responsibility** pattern decouples the sender of a request from its receivers by allowing multiple objects a chance to handle the request. The receiving objects are chained and the request is passed along the chain until an object handles it.

**Intent:** Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.

---

## Key Concepts

| Concept | Description |
|---|---|
| **Handler** | Defines an interface for handling requests and optionally holds a reference to the next handler |
| **Concrete Handler** | Handles requests it is responsible for; forwards the rest to the next handler |
| **Client** | Initiates the request and sends it to the first handler in the chain |
| **Chain** | The linked list of handlers through which requests travel |

---

## UML Structure

```
Client
  │
  ▼
┌─────────────────────┐
│      <<abstract>>   │
│        Logger       │
│─────────────────────│
│ - level: int        │
│ - nextLogger: Logger│
│─────────────────────│
│ + setNext(Logger)   │
│ + log(level, msg)   │
│ # write(msg) [abs]  │
└─────────────────────┘
          ▲
          │ extends
    ┌─────┴──────┐──────────────┬──────────────┐
    │            │              │              │
┌───────┐  ┌─────────┐  ┌──────────┐  ┌────────────┐
│ Debug │  │  Info   │  │   Warn   │  │   Error    │
│Logger │  │ Logger  │  │  Logger  │  │   Logger   │
└───────┘  └─────────┘  └──────────┘  └────────────┘
```

---

## Logger System Example

A logging system is a classic use case. Log messages have severity levels — each logger only handles messages at or above its configured level, then forwards the request down the chain.

### Log Levels

```
DEBUG (1)  <  INFO (2)  <  WARN (3)  <  ERROR (4)
```

---

### 1. Abstract Handler

```java
abstract class Logger {
    protected int level;
    protected Logger nextLogger;

    /**
     * Sets the next handler in the chain.
     * Returns the next logger to allow fluent chaining:
     *   debug.setNext(info).setNext(warn).setNext(error)
     */
    public Logger setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    /**
     * Core chain logic:
     *  - If this handler's level <= request level → handle it
     *  - Always forward to the next handler if one exists
     */
    public void log(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.log(level, message);
        }
    }

    // Each concrete handler implements its own output mechanism
    protected abstract void write(String message);
}
```

---

### 2. Concrete Handlers

```java
// Handles DEBUG and above — lowest priority
class DebugLogger extends Logger {
    public static final int DEBUG = 1;

    public DebugLogger() {
        this.level = DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("[DEBUG] " + message);
    }
}

// Handles INFO and above
class InfoLogger extends Logger {
    public static final int INFO = 2;

    public InfoLogger() {
        this.level = INFO;
    }

    @Override
    protected void write(String message) {
        System.out.println("[INFO]  " + message);
    }
}

// Handles WARN and above
class WarnLogger extends Logger {
    public static final int WARN = 3;

    public WarnLogger() {
        this.level = WARN;
    }

    @Override
    protected void write(String message) {
        System.out.println("[WARN]  " + message);
    }
}

// Handles ERROR only — highest priority, writes to stderr
class ErrorLogger extends Logger {
    public static final int ERROR = 4;

    public ErrorLogger() {
        this.level = ERROR;
    }

    @Override
    protected void write(String message) {
        System.err.println("[ERROR] " + message);
    }
}
```

---

### 3. Client — Building the Chain

```java
public class Main {

    /**
     * Assembles the chain: DEBUG → INFO → WARN → ERROR
     * Returns the head of the chain (DebugLogger).
     */
    private static Logger buildChain() {
        Logger debug = new DebugLogger();
        Logger info  = new InfoLogger();
        Logger warn  = new WarnLogger();
        Logger error = new ErrorLogger();

        debug.setNext(info)
             .setNext(warn)
             .setNext(error);

        return debug; // head of chain
    }

    public static void main(String[] args) {
        Logger logger = buildChain();

        System.out.println("--- Logging DEBUG ---");
        logger.log(1, "Entering method calculateTotal()");

        System.out.println("\n--- Logging INFO ---");
        logger.log(2, "User 'alice' logged in successfully");

        System.out.println("\n--- Logging WARN ---");
        logger.log(3, "Disk usage above 80%");

        System.out.println("\n--- Logging ERROR ---");
        logger.log(4, "Database connection failed!");
    }
}
```

---

### Output

```
--- Logging DEBUG ---
[DEBUG] Entering method calculateTotal()
[INFO]  Entering method calculateTotal()
[WARN]  Entering method calculateTotal()
[ERROR] Entering method calculateTotal()

--- Logging INFO ---
[INFO]  User 'alice' logged in successfully
[WARN]  User 'alice' logged in successfully
[ERROR] User 'alice' logged in successfully

--- Logging WARN ---
[WARN]  Disk usage above 80%
[ERROR] Disk usage above 80%

--- Logging ERROR ---
[ERROR] Database connection failed!
```

> **Why does DEBUG print to all loggers?**  
> Because every logger in the chain has `level ≤ 1 (DEBUG)` — false for none of them — wait, actually `level ≤ request_level`. DEBUG (1) ≥ all handler levels (1, 2, 3, 4)? No — each handler fires only if `handler.level ≤ request.level`. So DEBUG(1) fires only DebugLogger(1). INFO(2) fires Debug(1) and Info(2). And so on upward. The output above assumes all handlers accumulate upward (each higher handler also fires for lower levels) — adjust the comparison operator in `log()` to change this behavior.

---

## How the Chain Works

```
Request: log(WARN=3, "Disk usage 80%")

  ┌──────────────┐
  │  DebugLogger │  handler.level=1 ≤ request.level=3? YES → [DEBUG] prints
  └──────┬───────┘
         │ forward
  ┌──────▼───────┐
  │  InfoLogger  │  handler.level=2 ≤ request.level=3? YES → [INFO]  prints
  └──────┬───────┘
         │ forward
  ┌──────▼───────┐
  │  WarnLogger  │  handler.level=3 ≤ request.level=3? YES → [WARN]  prints
  └──────┬───────┘
         │ forward
  ┌──────▼───────┐
  │  ErrorLogger │  handler.level=4 ≤ request.level=3? NO  → skips
  └──────────────┘
         ✗ end of chain
```

To make each logger handle **only its exact level** (exclusive mode), change the condition in `log()`:

```java
// Inclusive mode (current): handle this level AND pass forward
if (this.level <= level) { write(message); }

// Exclusive mode: ONLY handle exact level match, stop the chain
if (this.level == level) {
    write(message);
    return; // do not forward
}
```

---

## When to Use

| Use Case | Description |
|---|---|
| **Logging systems** | Route messages to console, file, or alert based on severity |
| **Auth middleware** | Chain: API key check → JWT validation → Role authorization |
| **UI event bubbling** | Events propagate from child → parent → root components |
| **Support escalation** | Ticket flows: L1 agent → L2 specialist → L3 engineer |
| **Input validation** | Run validators in sequence; stop on first failure |
| **Filter pipelines** | Apply transformations or checks to data in order |

---

## Pros & Cons

### ✅ Advantages

- **Decoupling** — The sender doesn't need to know which handler processes its request
- **Single Responsibility** — Each handler focuses on one specific task
- **Open/Closed Principle** — Add new handlers without modifying existing ones
- **Flexible chain order** — Rearrange or reconfigure the chain at runtime
- **Controlled propagation** — Handlers can stop or continue forwarding as needed

### ❌ Disadvantages

- **No guarantee of handling** — A request may reach the end of the chain unhandled
- **Debugging complexity** — Tracing which handler processed a request can be difficult
- **Performance overhead** — Long chains introduce latency for each forwarded request
- **Chain misconfiguration** — Incorrectly ordered or missing handlers cause subtle bugs

---

## Related Patterns

| Pattern | Relationship |
|---|---|
| **Command** | Can be used together — commands travel through a chain of handlers |
| **Decorator** | Similar structure, but Decorator always wraps behavior; CoR can stop the chain |
| **Composite** | CoR chains are often built using Composite tree structures |
| **Observer** | Both decouple sender from receiver, but Observer broadcasts to all; CoR stops at one |

---

*Pattern Category: **Behavioral** | Language: **Java** | Difficulty: **Intermediate***