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


## Pub Sub System
## ATM
## Elevator
## Spotify
## Movie Ticket Booking System
## Restaurant Management System
## Online Shopping System Like Amazon
## Online Stock Brokerage System
## Logging Framework
## Chess Game
## Tic-tac-toe


---

# All Design Patterns

https://github.com/iluwatar/java-design-patterns


### Creational Patterns
These patterns are concerned with the process of object creation, providing ways to create objects while hiding the creation logic, rather than instantiating objects directly using the `new` operator.

* **Abstract Factory:** Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
* **Builder:** Separates the construction of a complex object from its representation so that the same construction process can create different representations.
* **Factory:** Creates objects without exposing the instantiation logic to the client and refers to the newly created object using a common interface. (Often used as a simplified version of Factory Method).
* **Factory Kit:** An interface-based approach to building factories.
* **Factory Method:** Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
* **Multiton:** A variation of the Singleton pattern that ensures a limited number of instances of a class, each accessible with a key.
* **Object Pool:** Reuses and shares objects that are expensive to create.
* **Prototype:** Creates new objects by copying an existing object, known as the prototype.
* **Singleton:** Ensures a class has only one instance and provides a global point of access to it.
* **Step Builder:** A type of builder that guides the user through the creation of an object in a specific order of steps.

### Structural Patterns
These patterns deal with object composition, assembling objects and classes into larger structures while keeping these structures flexible and efficient.

* **Adapter:** Allows incompatible interfaces to work together.
* **Bridge:** Decouples an abstraction from its implementation so that the two can vary independently.
* **Composite:** Composes objects into tree structures to represent part-whole hierarchies.
* **Decorator:** Adds new functionality to an object dynamically without altering its structure.
* **Delegation:** An object expresses certain behavior to the outside but in reality delegates responsibility for implementing that behavior to an associated object.
* **Facade:** Provides a simplified, higher-level interface to a complex subsystem.
* **Flyweight:** Reduces the cost of creating and manipulating a large number of similar objects by sharing them.
* **Private Class Data:** Restricts accessor/mutator access to a class’s data.
* **Property:** Implements properties in Java, which lack native support for them.
* **Proxy:** Provides a surrogate or placeholder for another object to control access to it.
* **Virtual Proxy:** A placeholder for "expensive" objects. The real object is only created when it is truly needed.

### Behavioral Patterns
These patterns are concerned with algorithms and the assignment of responsibilities between objects, describing how objects interact and communicate.

* **Acyclic Visitor:** A variation of the Visitor pattern that breaks dependency cycles.
* **Balking:** An object will only execute an action when it is in a particular state.
* **Callback:** A mechanism where a function is passed as an argument to be executed after another operation has been completed.
* **Chain of Responsibility:** Creates a chain of receiver objects for a request, allowing multiple objects a chance to handle it.
* **Command:** Encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
* **Curiously Recurring Template Pattern (CRTP):** A C++ idiom ported to Java where a class `X` derives from a template class instantiation using `X` itself as a template argument.
* **Double Dispatch:** A mechanism that dispatches a function call to different concrete functions depending on the runtime types of two objects involved in the call.
* **Execute Around:** A pattern that ensures a pair of operations (e.g., resource allocation/deallocation) are performed before and after a given block of code.
* **Interpreter:** Given a language, defines a representation for its grammar along with an interpreter that uses the representation to interpret sentences in the language.
* **Iterator:** Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
* **Mediator:** Defines a simplified communication channel between multiple objects, so they no longer need to refer to each other explicitly.
* **Memento:** Captures and externalizes an object's internal state so that the object can be restored to this state later.
* **Mute Idiom:** A technique for suppressing exceptions by wrapping a call in a try-catch block that does nothing.
* **Null Object:** Provides an object that acts as a default value or a neutral "do nothing" behavior, avoiding the need for `null` checks.
* **Observer (Publish-Subscribe):** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
* **Poison Pill:** A special message placed on a queue that causes the consumer of that message to shut down.
* **Resource Acquisition Is Initialization (RAII):** Ensures that a resource is properly released by tying its lifecycle to the scope of an object.
* **Role Object:** Adapts a central object for a specific client's needs by attaching a "role" object that provides a specialized interface.
* **Servant:** Defines common functionality for a group of classes that cannot be modified.
* **Specification:** A pattern where business rules can be chained together using boolean logic.
* **State:** Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
* **Strategy:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
* **Subclass Sandbox:** A pattern for developing subclasses in a "sandbox" environment, providing controlled access to necessary resources.
* **Template Method:** Defines the skeleton of an algorithm in an operation, deferring some steps to subclasses.
* **Visitor:** Represents an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.

### Architectural Patterns
These patterns describe a fundamental structural organization for software systems. They are high-level patterns that shape the entire application.

* **Clean Architecture:** A software architecture philosophy that separates the elements of a design into ring-like levels, with the core business logic at the center.
* **Domain Model:** An object model of the domain that incorporates both behavior and data.
* **Event-Driven Architecture:** An architecture based on producing, detecting, consuming, and reacting to events.
* **Hexagonal Architecture (Ports and Adapters):** Allows an application to be equally driven by users, programs, automated tests, or batch scripts, and to be developed and tested in isolation from its eventual run-time devices and databases.
* **Layered Architecture:** Organizes software into layers, each with a specific responsibility.
* **Microservices Architecture Patterns:** A collection of patterns specifically for building applications as a suite of small, independent services.
    * **Microservices Aggregator**
    * **Microservices API Gateway**
    * **Microservices Client-Side UI Composition**
    * **Microservices Distributed Tracing**
    * **Microservices Idempotent Consumer**
    * **Microservices Log Aggregation**
* **Monolithic Architecture:** Building an application as a single, indivisible unit.
* **Naked Objects:** An architectural pattern where the business objects are exposed directly to the user interface, without a separate presentation layer.
* **Saga:** A sequence of local transactions where each transaction updates data within a single service and publishes a message or event to trigger the next transaction step.

### Concurrency Patterns
These patterns deal with multi-threaded programming and the challenges of simultaneous operations.

* **Active Object:** Decouples method execution from method invocation for objects that reside in their own thread of control.
* **Actor Model:** A model of concurrent computation that treats "actors" as the universal primitives of concurrent computation.
* **Async Method Invocation:** A pattern that allows a caller to invoke a long-running method without blocking, often using callbacks or futures to get the result.
* **Backpressure:** A mechanism to handle data streams by allowing a consumer to signal to a producer that it is becoming overwhelmed, thus slowing down the data rate.
* **Double-Checked Locking:** A technique to reduce the overhead of acquiring a lock by first testing the locking criterion without actually acquiring the lock.
* **Event Queue:** A queue that stores events in the order they are created and processes them sequentially.
* **Fan-out/Fan-in:** A pattern where a task is fanned out to multiple concurrent workers, and then the results are fanned in (aggregated).
* **Guarded Suspension:** Manages an object's state such that a method will wait (suspend) until a certain condition is true before proceeding.
* **Half-Sync/Half-Async:** A pattern that structures a system into a synchronous layer and an asynchronous layer to simplify concurrent programming.
* **Leader-Followers:** A concurrency pattern that provides a mechanism for event demultiplexing and dispatching in which a pool of threads share a set of event sources.
* **Lockable Object:** An object that can be locked to prevent concurrent access by multiple threads.
* **Master-Worker:** A pattern where a master distributes work to multiple identical worker processes and aggregates the results.
* **Monitor:** An object or module intended to be used safely by more than one thread.
* **Producer-Consumer:** A classic concurrency problem solution where one or more producers generate data and place it in a shared buffer, and one or more consumers retrieve data from the buffer.
* **Promise:** Represents the eventual result of an asynchronous operation.
* **Reactor:** A pattern for handling service requests delivered concurrently to an application by one or more clients.
* **Thread Pool Executor:** Manages a pool of worker threads to execute asynchronous tasks.
* **Throttling:** Controls the rate at which a service is used to prevent it from being overwhelmed.

### Integration and Distributed Systems Patterns
These patterns are focused on how different systems or components communicate and integrate with each other, especially in a distributed environment.

* **Ambassador:** A helper service that is deployed alongside a client, acting as a proxy for network requests to a remote service.
* **Anti-Corruption Layer:** A layer of code that acts as a translator between two systems, ensuring that one system's domain model is not polluted by the other's.
* **Business Delegate:** Decouples the presentation tier from the business tier by providing a client-side abstraction for remote services.
* **Circuit Breaker:** A pattern used to detect failures and prevent a failure from constantly recurring during maintenance, temporary external system failure or unexpected system difficulties.
* **Client Session:** Manages client-specific state and information across multiple requests.
* **Command Query Responsibility Segregation (CQRS):** A pattern that separates read and update operations for a data store.
* **Data Bus:** A central component that enables communication and data sharing between different modules or services without them having direct dependencies on each other.
* **Event Aggregator:** A single object that subscribes to multiple event sources and re-publishes them as a single stream.
* **Event Sourcing:** A pattern where all changes to application state are stored as a sequence of events, rather than just the current state.
* **Feature Toggle:** A technique to turn features on or off during runtime, without deploying new code.
* **Gateway:** An encapsulating object that routes commands and messages to the correct destination.
* **Health Check:** A pattern where a system exposes an endpoint that can be used to check its health or status.
* **Partial Response:** A pattern where the client can request a subset of the fields of a resource to reduce network bandwidth.
* **Queue-Based Load Leveling:** Uses a queue to buffer requests between a client and a service, smoothing out intermittent heavy loads.
* **Separated Interface:** Defines separate interfaces for different roles that a class plays.
* **Server Session:** Manages server-side state for a particular client session.
* **Service Locator:** A centralized registry that allows services to be looked up by name.
* **Sharding:** A database partitioning technique that separates very large databases into smaller, faster, more easily managed parts called data shards.
* **Strangler:** A pattern for incrementally migrating a legacy system by gradually replacing specific pieces of functionality with new applications and services.
* **Tolerant Reader:** A pattern for designing services that are tolerant of changes in the data schemas they consume.

### Data Access Patterns
These patterns provide solutions for accessing and manipulating data, often abstracting the underlying data source (like a database).

* **Caching:** Temporarily storing data to reduce the need to fetch it from its original source again.
* **Converter:** A pattern that converts objects from one type to another, often between domain objects and DTOs.
* **Data Access Object (DAO):** An object that provides an abstract interface to some type of database or other persistence mechanism.
* **Data Locality:** A principle of organizing data to be physically close to where it is processed.
* **Data Mapper:** A layer of mappers that moves data between objects and a database while keeping them independent of each other.
* **Data Transfer Object (DTO):** An object that carries data between processes.
* **Identity Map:** Ensures that each object gets loaded only once by keeping every loaded object in a map.
* **Lazy Loading:** A design pattern that defers initialization of an object until the point at which it is needed.
* **Metadata Mapping:** Holds metadata about object-relational mappings in a separate layer.
* **Optimistic Offline Lock:** A pattern to manage concurrency by detecting conflicts between transactions at commit time.
* **Repository:** Mediates between the domain and data mapping layers using a collection-like interface for accessing domain objects.
* **Serialized LOB (Large Object):** Stores a graph of objects as a single large object (LOB) in a database field.
* **Single Table Inheritance:** Represents an inheritance hierarchy of classes as a single table.
* **Table Inheritance:** Represents an inheritance hierarchy of classes with one table per class.
* **Table Module:** An object that acts on a whole table of data in a database, with one instance per table.
* **Transaction Script:** Organizes business logic by procedures where each procedure handles a single request.
* **Unit of Work:** Maintains a list of objects affected by a business transaction and coordinates the writing out of changes.
* **Value Object:** An immutable object whose equality is not based on identity.
* **Version Number:** A technique to track the version of a record to prevent concurrent modification issues.

### Presentation Layer Patterns
These patterns deal with the user interface and how it interacts with the rest of the application.

* **Bloc (Business Logic Component):** A pattern that separates presentation from business logic, popular in Flutter but applicable elsewhere.
* **Composite View:** A view made up of smaller, reusable sub-views.
* **Context Object:** Encapsulates system information in a protocol-independent way to be shared across the application.
* **Flux:** An application architecture that Facebook uses for building client-side web applications, emphasizing a unidirectional data flow.
* **Front Controller:** Provides a centralized entry point for handling all requests.
* **Intercepting Filter:** Allows for pre-processing and post-processing of requests without modifying the core request handler.
* **Model-View-Controller (MVC):** Divides an application into three interconnected parts: the model (data), the view (UI), and the controller (input logic).
* **Model-View-Intent (MVI):** A reactive architectural pattern that emphasizes a unidirectional data flow and immutable state.
* **Model-View-Presenter (MVP):** A derivative of MVC where the presenter acts as the middleman between the model and the view.
* **Model-View-ViewModel (MVVM):** A pattern that facilitates a separation of GUI development from business logic development via data binding.
* **Page Controller:** An object that handles a request for a specific page or action on a web site.
* **Presentation Model:** Represents the state and behavior of the presentation independently of the UI controls used to display it.
* **Service to Worker:** Combines a service layer with the worker pattern for processing requests.
* **Session Facade:** A facade that encapsulates the interaction with business-tier components for a specific use case or session.
* **Template View:** Renders information into HTML by embedding markers in an HTML page.

### Functional Patterns
These patterns are derived from the functional programming paradigm and emphasize immutability, first-class functions, and avoiding side effects.

* **Collection Pipeline:** A sequence of operations on a collection of objects (e.g., filter, map, reduce).
* **Combinator:** A higher-order function that uses only function application and earlier defined combinators to define a result from its arguments.
* **Currying:** The technique of converting a function that takes multiple arguments into a sequence of functions that each takes a single argument.
* **Function Composition:** The act of combining simple functions to build more complicated ones.
* **Monad:** A structure that represents computations defined as sequences of steps.
* **Trampoline:** A technique for implementing tail-recursive functions in languages without tail-call optimization.

### Testing Patterns
These patterns are specifically designed to make code more testable and to structure tests effectively.

* **Arrange-Act-Assert (AAA):** A pattern for structuring unit tests into three distinct sections.
* **Object Mother:** A kind of factory used in tests to create example objects needed for a test.
* **Page Object:** An object-oriented pattern in test automation that models the pages of an application as objects.
* **Service Stub:** A stand-in for a real service used for testing purposes.

### Other Patterns and Idioms
This category includes patterns that don't fit neatly into the above categories or are more like specific implementation techniques or domain-specific solutions.

* **Abstract Document:** A way to handle properties in an object dynamically without a rigid class structure.
* **Bytecode:** The pattern of generating bytecode at runtime to create dynamic classes or proxies.
* **Collecting Parameter:** Uses a parameter to collect results from a series of recursive calls.
* **Component:** An object-oriented pattern for building modular systems, often used in game development.
* **Composite Entity:** A complex entity bean made up of a graph of dependent objects.
* **Dirty Flag:** A flag that indicates whether an object's state has been modified and needs to be saved.
* **Double Buffer:** A technique for drawing graphics without flicker by composing a frame in a back buffer before displaying it.
* **Extension Objects:** Allows adding new functionality to a class hierarchy without changing the classes themselves.
* **Fluent Interface:** An API design that results in code that is highly readable, much like natural language prose.
* **Game Loop:** The central control flow of a game or simulation, which processes input, updates game state, and renders the scene.
* **Localization:** The process of adapting software to a specific region or language.
* **Map-Reduce:** A programming model for processing and generating large data sets with a parallel, distributed algorithm.
* **Marker Interface:** An empty interface used to "mark" or tag classes with a specific attribute.
* **Money:** Represents a monetary value as an object to avoid floating-point precision issues.
* **Notification:** A pattern for sending messages to interested parties.
* **Parameter Object:** Groups a number of parameters into a single object.
* **Pipeline:** A chain of processing elements arranged so that the output of each element is the input of the next.
* **Registry:** A well-known object that other objects can use to find common objects and services.
* **Retry:** A pattern for automatically re-executing an operation that has failed.
* **Serialized Entity:** An entity that can be serialized and deserialized.
* **Service Layer:** Defines an application's boundary and its set of available operations from the perspective of interfacing client layers.
* **Spatial Partition:** A technique for dividing a 2D or 3D space into smaller, more manageable regions.
* **Special Case:** A subclass that provides special behavior for some cases, often used as an alternative to the Null Object pattern.
* **Twin:** A pattern that allows two objects to be strongly coupled, effectively acting as two sides of the same conceptual entity.
* **Type Object:** A pattern that allows for the flexible creation of new "types" of objects by creating a class to represent the type itself.
* **Update Method:** A method in game development that is called once per frame to update the state of a game object.
