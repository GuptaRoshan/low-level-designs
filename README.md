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

## LFU Cache

### LFU Cache Example (Capacity = 2)

| Step | Operation | Returned | Cache Content (key → (value, freq)) | Eviction Rule Applied                                            |
|------|-----------|----------|-------------------------------------|------------------------------------------------------------------|
| 1    | put(1,1)  | –        | {1 → (1, freq=1)}                   | – (cache not full)                                               |
| 2    | put(2,2)  | –        | {1 → (1, freq=1), 2 → (2, freq=1)}  | – (cache not full)                                               |
| 3    | get(1)    | 1        | {1 → (1, freq=2), 2 → (2, freq=1)}  | – (just freq increment)                                          |
| 4    | put(3,3)  | –        | {1 → (1, freq=2), 3 → (3, freq=1)}  | **Evict key=2** → lowest freq=1                                  |
| 5    | get(2)    | -1       | {1 → (1, freq=2), 3 → (3, freq=1)}  | – (already evicted)                                              |
| 6    | get(3)    | 3        | {1 → (1, freq=2), 3 → (3, freq=2)}  | – (just freq increment)                                          |
| 7    | put(4,4)  | –        | {3 → (3, freq=2), 4 → (4, freq=1)}  | **Evict key=1** → tie (keys 1 & 3 both freq=2) → **LRU = key 1** |
| 8    | get(1)    | -1       | {3 → (3, freq=2), 4 → (4, freq=1)}  | – (already evicted)                                              |
| 9    | get(3)    | 3        | {3 → (3, freq=3), 4 → (4, freq=1)}  | – (just freq increment)                                          |
| 10   | get(4)    | 4        | {3 → (3, freq=3), 4 → (4, freq=2)}  | – (just freq increment)                                          |


### 🔑 Key Observations

1. **Step 4 (put 3)** → Evicted `2` because it had the lowest frequency (freq=1 vs. key=1’s freq=2).
   *Rule: LFU eviction.*
2. **Step 7 (put 4)** → Both `1` and `3` had freq=2.

   Tie → Evict the **Least Recently Used (LRU)** → `1`.
   *Rule: LFU tie → LRU eviction.*



## LRU Cache


## Rate Limiter


## Pub Sub System


## File System


## Elevator


## Chess Game Engine


## Splitwise-style Expense Sharing System


## Logging System


## Tic Tac Toe


## ATM


## Vending Machine


## Ride Sharing