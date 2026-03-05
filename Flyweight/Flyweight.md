# Flyweight Design Pattern — Complete Reference Guide

> A concept-first, no-code reference to deeply understand the Flyweight pattern.

---

## Table of Contents

1. [What is Flyweight?](#1-what-is-flyweight)
2. [The Core Problem It Solves](#2-the-core-problem-it-solves)
3. [Intrinsic vs Extrinsic State](#3-intrinsic-vs-extrinsic-state)
4. [Structure & Participants](#4-structure--participants)
5. [Real World Analogies](#5-real-world-analogies)
6. [Real Production Use Cases](#6-real-production-use-cases)
7. [Java Built-in Flyweight Examples](#7-java-built-in-flyweight-examples)
8. [Memory Impact](#8-memory-impact)
9. [Flyweight vs Other Patterns](#9-flyweight-vs-other-patterns)
10. [When To Use / Not Use](#10-when-to-use--not-use)
11. [Common Mistakes](#11-common-mistakes)
12. [Quick Cheat Sheet](#12-quick-cheat-sheet)

---

## 1. What is Flyweight?

Flyweight is a **Structural Design Pattern** that reduces memory usage by **sharing common data** across many similar objects instead of storing duplicate data in each one.

### Category
- **Type:** Structural Pattern
- **GoF Book:** Design Patterns — Elements of Reusable Object-Oriented Software (1994)
- **Also Known As:** Cache Pattern, Object Sharing Pattern

### One-Line Summary
> Store repeated heavy data **once**, and let thousands of lightweight objects **point to it** — instead of each carrying their own copy.

### The Name Origin
The term *"Flyweight"* comes from boxing — the lightest weight class. The pattern makes objects as **light (lean) as possible** by stripping out everything that can be shared externally.

---

## 2. The Core Problem It Solves

### The Problem — Memory Explosion
When an application needs to create a **very large number of objects** that share the same data, memory gets wasted by storing identical information repeatedly in every single object.

### Without Flyweight
```
1,000,000 Tree objects
Each tree stores → name + color + texture(50MB) + x + y

Total = 1,000,000 × 50MB = 50,000 GB ❌ Impossible
```

### With Flyweight
```
1 TreeType object → stores name + color + texture(50MB)  ← shared once

1,000,000 Tree objects → each stores only x + y + reference to TreeType

Total = 50MB + tiny overhead ✅ Fits in RAM
```

---

## 3. Intrinsic vs Extrinsic State

This is the **single most important concept** in Flyweight. Everything revolves around splitting object data into two categories.

---

### Intrinsic State

| Property | Description |
|---|---|
| **Definition** | Data that is **the same** across many objects |
| **Where stored** | Inside the Flyweight object |
| **Mutability** | Always **immutable** — never changes |
| **Memory** | Stored **once**, shared by reference |
| **Examples** | color, texture, font-face, sprite image, tree type |

---

### Extrinsic State

| Property | Description |
|---|---|
| **Definition** | Data that is **unique** per object instance |
| **Where stored** | Outside the Flyweight — in the context object or passed at runtime |
| **Mutability** | Can change freely |
| **Memory** | Stored separately in each object |
| **Examples** | x/y position, size, velocity, ID, timestamp |

---

### Side-by-Side Comparison

| Aspect | Intrinsic | Extrinsic |
|---|---|---|
| Shared? | ✅ Yes | ❌ No |
| Stored in Flyweight? | ✅ Yes | ❌ No |
| Immutable? | ✅ Must be | ➖ Optional |
| Saves memory? | ✅ This is the point | ➖ Normal storage |
| Example (Tree) | name, color, texture | x position, y position |
| Example (Character) | font, size, color | row, column on screen |
| Example (Bullet) | shape, color, sprite | velocity, direction, position |

---

### Visual Breakdown

```
┌──────────────────────────────────────────────────────┐
│                    Tree Object                       │
│                                                      │
│   ┌────────────────────┐    ┌────────────────────┐   │
│   │  Intrinsic State   │    │  Extrinsic State   │   │
│   │  (in Flyweight)    │    │  (in Context)      │   │
│   │                    │    │                    │   │
│   │  name = "Oak"      │    │  x = 105           │   │
│   │  color = "Green"   │    │  y = 230           │   │
│   │  texture = 50MB    │    │                    │   │
│   │                    │    │                    │   │
│   │  Shared by ALL     │    │  Unique per tree   │   │
│   │  Oak trees ✅      │    │                    │   │
│   └────────────────────┘    └────────────────────┘   │
└──────────────────────────────────────────────────────┘
```

---

## 4. Structure & Participants

### Structural Diagram

```
Client
  │
  ▼
FlyweightFactory ──────── HashMap / Cache
  │                       (key → Flyweight)
  │
  ▼
Flyweight  ← interface
  │
  ├── ConcreteFlyweight     → holds intrinsic state (shared)
  └── UnsharedFlyweight     → not all objects need to be shared

Context Object
  ├── extrinsic state (x, y, id...)
  └── reference → ConcreteFlyweight
```

---

### Participants & Their Roles

| Participant | Role |
|---|---|
| **Flyweight** | Interface or abstract class. Declares method that accepts extrinsic state as a parameter. |
| **ConcreteFlyweight** | Implements Flyweight. Stores intrinsic state. Must be **immutable and thread-safe**. |
| **UnsharedFlyweight** | Optional. Flyweight that is NOT shared — used when sharing doesn't make sense for certain objects. |
| **FlyweightFactory** | Creates and manages the flyweight cache. Returns existing flyweight if it already exists. Never creates duplicates. |
| **Context** | Stores the extrinsic state. Holds a reference to a flyweight. Many contexts can point to the same flyweight. |
| **Client** | Computes or stores extrinsic state. Uses FlyweightFactory to get flyweights. Passes extrinsic state when calling flyweight methods. |

---

### How the Factory Works (Logic Flow)

```
Client requests a Flyweight with key "Oak-Green"
         │
         ▼
FlyweightFactory checks cache
         │
    ┌────┴────┐
    │         │
  HIT       MISS
    │         │
    │         ▼
    │    Create new ConcreteFlyweight
    │    Store in cache
    │         │
    └────┬────┘
         │
         ▼
  Return Flyweight to Client
```

---

## 5. Real World Analogies

### Analogy 1 — Library Books
A library has **one copy** of a book that **many people read** (share), instead of printing a new copy for every reader. The book content (intrinsic) is shared. The reader's bookmark position (extrinsic) is personal.

---

### Analogy 2 — Font Rendering
A word processor loads a **font file once** into memory. Every character on screen shares that font object. Only the character's row and column on screen differ.

---

### Analogy 3 — Chess Pieces
In a chess game, all **white pawns look identical**. Instead of creating 8 separate pawn objects with the same image and color, you create **one shared pawn object** and only track each pawn's board position separately.

---

### Analogy 4 — Starbucks Menu
A Starbucks menu board is **one shared object** that all 200 customers in the store read. Each customer's **order** (extrinsic) is personal, but the menu (intrinsic) is shared.

---

## 6. Real Production Use Cases

### Game Development
- **Particles** — explosions, fire, smoke with thousands of identical particles
- **Tiles** — map tiles (grass, water, stone) rendered millions of times
- **Trees & Vegetation** — forests with millions of trees sharing textures
- **Bullets / Projectiles** — hundreds of bullets sharing the same sprite

### Text & Document Rendering
- **Character rendering** — every 'A' on screen shares the same glyph data
- **Font objects** — one font object shared across an entire document
- **Text editors** — VS Code, IntelliJ share character/glyph flyweights internally

### UI Frameworks
- **Icons** — same icon object reused across thousands of list items
- **Cell renderers** — table/grid cells reuse a single renderer object
- **Swing JTable** — uses flyweight for cell rendering internally

### Networking & Systems
- **Connection pools** — database connections shared across requests
- **Thread pools** — reusing threads instead of creating new ones each time
- **HTTP keep-alive** — reusing TCP connections across multiple requests

### Maps & GIS
- **Google Maps tiles** — same map tile served to millions of users
- **Pin/marker icons** — thousands of map markers sharing the same icon object

---

## 7. Java Built-in Flyweight Examples

Java itself uses Flyweight heavily under the hood.

---

### String Pool
Java maintains a **string pool** (intern pool) where string literals are stored once and reused.

- `"hello" == "hello"` → **true** — same object from pool
- `new String("hello") == new String("hello")` → **false** — bypasses pool
- `String.intern()` — manually adds a string to the pool

---

### Integer Cache
Java caches **Integer objects from -128 to 127**. Any autoboxing within this range returns the same cached object.

- `Integer.valueOf(100) == Integer.valueOf(100)` → **true** — cached flyweight
- `Integer.valueOf(200) == Integer.valueOf(200)` → **false** — outside cache range
- The cache range can be extended with JVM flag: `-XX:AutoBoxCacheMax=<N>`

---

### Other Cached Wrappers
- **Boolean** — `Boolean.TRUE` and `Boolean.FALSE` are singletons (flyweights)
- **Byte** — all 256 values are cached
- **Short** — -128 to 127 cached
- **Character** — 0 to 127 cached
- **Long** — -128 to 127 cached

---

### Swing & AWT
- `Color` constants like `Color.RED`, `Color.BLUE` are shared flyweights
- `Font` objects are designed to be shared across components
- `JTable` cell renderers use flyweight — one renderer object handles all cells

---

## 8. Memory Impact

### Formula

```
Without Flyweight:
  Total Memory = N objects × (intrinsic size + extrinsic size)

With Flyweight:
  Total Memory = K unique types × intrinsic size
               + N objects × extrinsic size only

Where:
  N = total number of objects
  K = number of unique flyweight types (K << N)
```

---

### Concrete Example at Scale

| Scenario | Without Flyweight | With Flyweight |
|---|---|---|
| 1M trees, 10 types, 50MB texture each | 50,000 GB | ~508 MB |
| 1M characters, 26 glyphs, 1MB glyph each | 1,000 GB | ~26 MB |
| 1M bullets, 5 types, 10MB sprite each | 10,000 GB | ~50 MB |
| 1M map tiles, 20 types, 5MB texture each | 5,000 GB | ~100 MB |

---

### Memory Savings Rule of Thumb

```
Savings % = (1 - K/N) × 100

If N = 1,000,000 trees and K = 10 types:
Savings = (1 - 10/1,000,000) × 100 = 99.999% memory saved
```

The **fewer unique types relative to total objects**, the **greater the savings**.

---

## 9. Flyweight vs Other Patterns

| Pattern | Intent | Key Difference from Flyweight |
|---|---|---|
| **Singleton** | Only one instance of a class exists | One instance globally; Flyweight has multiple shared instances (one per type) |
| **Prototype** | Clone objects to avoid expensive creation | Prototype copies; Flyweight shares — no copies |
| **Facade** | Simplify a complex subsystem | About API simplicity, not memory optimization |
| **Proxy** | Control access to another object | Proxy wraps one object; Flyweight shares many |
| **Object Pool** | Reuse objects to avoid creation cost | Pool recycles objects; Flyweight shares read-only state |
| **Cache** | Store results to avoid recomputation | Cache is broader; Flyweight is specifically about sharing object state |

---

### Flyweight + Other Patterns (Common Combinations)

- **Flyweight + Factory** — The FlyweightFactory is almost always a Factory. This is the standard combination.
- **Flyweight + Composite** — Leaf nodes in a Composite tree are often Flyweights.
- **Flyweight + State** — State objects with no instance variables can be shared as Flyweights.
- **Flyweight + Strategy** — Stateless Strategy objects can be implemented as Flyweights.

---

## 10. When To Use / Not Use

### ✅ Use Flyweight When

- Your application creates a **very large number of objects** (thousands to millions)
- These objects share a **significant amount of repeated data**
- **Memory consumption is a bottleneck** or a concern
- Object identity doesn't matter — you don't need `obj1 == obj2` to be true
- The shared state can be made **truly immutable**
- Examples: game engines, text renderers, map renderers, particle systems

---

### ❌ Do NOT Use Flyweight When

- You have only a **small number of objects** — overhead isn't worth it
- Each object's data is **completely unique** — nothing to share
- You need **object identity** (each object must be distinguishable by reference)
- The intrinsic state **needs to change** — Flyweights must be immutable
- You're doing **premature optimization** — always measure first
- Simple CRUD applications with no memory pressure

---

## 11. Common Mistakes

### Mistake 1 — Making Flyweight Mutable
Flyweight objects **must be immutable**. If one object modifies the shared state, it affects all objects pointing to it. Always mark intrinsic fields as `final`.

---

### Mistake 2 — Storing Extrinsic State Inside Flyweight
Extrinsic state must live **outside** the Flyweight. If you store position, ID, or any context-specific data inside the flyweight, it can no longer be shared.

---

### Mistake 3 — Not Using a Factory
Without a FlyweightFactory, clients might accidentally create duplicate Flyweights, defeating the entire purpose of the pattern. Always gate creation through the factory.

---

### Mistake 4 — Using It for Small Object Counts
If you only have 50 objects, Flyweight adds complexity with zero benefit. Only use it when you have **thousands or millions** of objects with shared state.

---

### Mistake 5 — Confusing with Singleton
Singleton = one instance of a class, globally. Flyweight = one instance **per unique type**. A system can have many different Flyweight objects (one per tree type, one per character, etc.).

---

### Mistake 6 — Forgetting Thread Safety
Since Flyweight objects are shared across many contexts and possibly many threads, the **FlyweightFactory must be thread-safe**. Use `ConcurrentHashMap` or synchronization in Java.

---

## 12. Quick Cheat Sheet

### Pattern at a Glance

| Property | Value |
|---|---|
| **Category** | Structural |
| **Purpose** | Reduce memory by sharing common object state |
| **Key Concept** | Intrinsic (shared) vs Extrinsic (unique) state |
| **Main Component** | FlyweightFactory with a cache (HashMap) |
| **Objects Created** | One per unique type, not one per instance |
| **Mutability** | Flyweight must be immutable |
| **Thread Safety** | Factory must be thread-safe |

---

### Decision Checklist

```
Ask yourself these questions:

[ ] Do I have thousands or millions of similar objects?
[ ] Do these objects share significant repeated data?
[ ] Can the shared data be made immutable?
[ ] Is memory usage a real concern in my application?
[ ] Can I cleanly separate shared state from unique state?

If you answered YES to most → Use Flyweight ✅
If you answered NO to most  → Skip it ❌
```

---

### Key Terms to Remember

| Term | Meaning |
|---|---|
| **Flyweight** | The shared object holding intrinsic state |
| **Intrinsic State** | Shared, immutable data stored inside the Flyweight |
| **Extrinsic State** | Unique, context-specific data stored outside |
| **FlyweightFactory** | Cache manager — creates and returns shared Flyweights |
| **Context** | The lightweight object holding extrinsic state + Flyweight reference |
| **Object Pool** | Related concept — reuses objects; Flyweight shares read-only data |

---

### Summary in Plain English

> You have 1,000,000 objects. They all look the same except for their position.
> Don't store color and texture 1,000,000 times.
> Store color and texture **once** in a shared object.
> Store only the position in each of your 1,000,000 objects.
> Have every object hold a **reference** to the shared object.
> That's Flyweight.

---
