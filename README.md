# Low-Level Design References

- [Leetcode Low-Level Design](https://leetcode.com/tag/design/)
- [Ashis Low-Level Design](https://github.com/ashishps1/awesome-low-level-design)
- [LLD Problems and Solution](https://github.com/prasadgujar/low-level-design-primer/blob/master/solutions.md)

---

# Design Patterns

[SourceMaking: Design Patterns](https://sourcemaking.com/design_patterns)

**What are design patterns?**

Design patterns are general reusable solutions to common problems that occur during software development. They represent
best practices for designing and structuring code to solve certain types of problems.

### **Creational Patterns**

| **Pattern**          | **Use Case / Scenario**                                                                         |
|----------------------|-------------------------------------------------------------------------------------------------|
| **Singleton**        | Ensure only one instance (e.g., **database connection**).                                       |
| **Factory Method**   | Create objects of different types without specifying the exact class (e.g., **UI components**). |
| **Abstract Factory** | Create families of related objects (e.g., **cross-platform UI**).                               |
| **Builder**          | Construct complex objects step by step (e.g., **building a meal**).                             |
| **Prototype**        | Clone objects from a prototype (e.g., **creating new game characters**).                        |
| **Flyweight**        | Share objects to save memory (e.g., **icons in a UI**).                                         |


### **Structural Patterns**

| **Pattern**   | **Use Case / Scenario**                                                             |
|---------------|-------------------------------------------------------------------------------------|
| **Adapter**   | Convert interfaces to fit requirements (e.g., **legacy system** integration).       |
| **Bridge**    | Separate abstraction from implementation (e.g., **UI on different platforms**).     |
| **Composite** | Treat individual objects and compositions uniformly (e.g., **file system**).        |
| **Decorator** | Add functionality to objects dynamically (e.g., **UI element enhancements**).       |
| **Facade**    | Simplify complex subsystems with a single interface (e.g., **library management**). |
| **Proxy**     | Control access to an object (e.g., **remote services**).                            |


### **Behavioral Patterns**

| **Pattern**                 | **Use Case / Scenario**                                                                                                   |
|-----------------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Chain of Responsibility** | Pass requests through a chain of handlers (e.g., **event handling**).                                                     |
| **Command**                 | Encapsulate a request as an object, allowing execution at different times (e.g., **action queues**, **task scheduling**). |
| **Iterator**                | Traverse a collection without exposing its details (e.g., **looping through data**).                                      |
| **Mediator**                | Centralize communication between objects (e.g., **chat systems**).                                                        |
| **Memento**                 | Capture the state of an object to restore it later (e.g., **saving game progress**, **undo/redo functionality**).         |
| **Observer**                | Notify multiple objects when one object changes (e.g., **UI updates on data change**).                                    |
| **State**                   | Change behavior based on an object's state (e.g., **game character behavior**).                                           |
| **Strategy**                | Switch between different algorithms (e.g., **payment methods**).                                                          |
| **Template Method**         | Define the outline of an algorithm and let subclasses implement specific steps (e.g., **data processing**).               |
| **Visitor**                 | Add new operations to objects without modifying them (e.g., **calculating taxes for products**).                          |


---

# Class Diagram in UML

A **Class Diagram** is a type of UML diagram that represents the **static structure** of a system. It shows **classes,
attributes, methods (functions), and relationships** between objects.

### **1. Components of a Class Diagram**

A class diagram consists of:

1. **Classes** – Represent entities in the system.

2. **Attributes** – Represent the **data members** of a class.

3. **Methods (Operations)** – Represent the **functions** of a class.

4. **Relationships** – Define how classes interact:

    - **Association** (one class uses another)

    - **Aggregation** (a "whole-part" relationship)

    - **Composition** (stronger "whole-part" relationship)

    - **Inheritance (Generalization)** (one class extends another)

    - **Multiplicity** (defines how many instances participate in a relationship)

### **2. Class Diagram Notation**

```
+----------------------+
| ClassName            |  <-- (Class Name)
+----------------------+
| - attribute1: Type   |  <-- (Attributes)
| - attribute2: Type   |  
+----------------------+
| + method1(): Type    |  <-- (Methods)
| + method2(): Type    |  
+----------------------+

```

- **`+` (Public)** → Accessible everywhere.
- **`-` (Private)** → Accessible only within the class.
- **`#` (Protected)** → Accessible within the class and its subclasses.


### **2. Relationships in Class Diagrams**


Here’s the information in a table format:

| **Relationship Type**               | **Symbol**  | **Definition**                                                                                    | **Example**                                                                     | **Diagram Representation**     |
|-------------------------------------|-------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|--------------------------------|
| **Association**                     | `------>`   | A direct link between two classes showing a relationship.                                         | A `Customer` places an `Order`.                                                 | `Customer  ------>  Order`     |
| **Aggregation** (Weak Whole-Part)   | `-----<>`   | One class contains another, but the contained class can exist independently.                      | A `Library` has multiple `Books`, but a `Book` can exist outside the `Library`. | `Library  ----<>  Book`        |
| **Composition** (Strong Whole-Part) | `------◆`   | A stronger form of aggregation where the contained object **cannot exist** without the container. | A `Car` has an `Engine`, and an **Engine cannot exist without a Car**.          | `Car  ----◆  Engine`           |
| **Inheritance (Generalization)**    | `------\>`  | One class derives from another (parent-child relationship).                                       | A `Librarian` and `Member` inherit from `Person`.                               | `Librarian  ----\>  Person`    |                             |
| **Dependency**                      | `--uses-->` | One class **relies** on another temporarily.                                                      | A `Customer` depends on `Payment` to complete an order.                         | `Customer  --uses-->  Payment` |


---

# Low Level Design Problems


## 🚗 **Transportation & Delivery**

- Ride-Sharing App (e.g., Uber)
- Food Delivery App (e.g., Swiggy, Zomato)

## 📱 **Social Media**

- WhatsApp
- Twitter
- Facebook

## 💬 **Communication Tools**

- Zoom
- Google Docs
- Gmail
- Event Calendar

## 🎬 **Entertainment**

- Video Streaming Platform
- Music Streaming App (e.g., Spotify)

## 🧰 **Utilities & Productivity**

- Dropbox (Cloud Storage)
- Splitwise (Expense Sharing)
- Google Maps (Navigation)
- Blockchain System

## 🏢 **Management Systems**

- Parking Lot
- Car Rental
- ATM
- Restaurant Management
- Airline Management

## ⏱️ **Real-Time & Scheduling**

- Elevator System
- Traffic Signal Control

## 🛒 **E-Commerce & Transactions**

- Vending Machine
- Online Shopping (e.g., Amazon)
- Movie Ticket Booking

## 🎮 **Games**

- Tic Tac Toe
- Snake & Ladder
- Chess
